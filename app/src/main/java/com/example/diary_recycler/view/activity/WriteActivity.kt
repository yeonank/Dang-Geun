package com.example.diary_recycler.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.bumptech.glide.Glide
import com.example.diary_recycler.RealPathUtil
import com.example.diary_recycler.SqliteHelper
import com.example.diary_recycler.SwipeAdapter
import com.example.diary_recycler.databinding.ActivityWriteBinding
import com.example.diary_recycler.view.fragment.HomeFragment
import java.io.File
import java.util.*



class WriteActivity() : AppCompatActivity(){
    var helper:SqliteHelper? = null
    val PERMISSION_Album = 101
    var f: File?=null
    val random = Random()
    var key: String = "prof"+ random.nextInt(100000000)

    private val binding: ActivityWriteBinding by lazy {
        ActivityWriteBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.e("writeactivity.oncreate", "1")

        setSupportActionBar(binding.toolbar2)
        setTitle("글쓰기")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar2.navigationIcon?.apply {

                setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN) //뒤로가기 아이콘 색 설정
        }


        val title_et= binding.edTitle
        val content_et=binding.edContent


        binding.imgBtn.setOnClickListener{
            requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_Album) //이미지 앨범에서 가져오기
        }

        //데이터 homeFragment로 전송
        binding.btnSave.setOnClickListener {
            if(content_et.text.toString().isNotEmpty()){
                Log.e("writeActivity.send", "data sending start")
                // uploadFile()

                val bundle = Bundle()
                bundle.putString("content", content_et.text.toString())
                bundle.putString("title", title_et.text.toString())

                if(f!=null) {
                  bundle.putString(
                      "img",
                      "https://fofuploadtest.s3.ap-northeast-2.amazonaws.com/" + key //aws s3 이미지 uri
                  )
                    Log.e("이미지 업로드", "확인"+f.toString())
                    uploadImg()
                }
                else bundle.putString("img",null) //이미지 없으면 null로 저장

                val home = HomeFragment()

                home.swipeadapter = SwipeAdapter(this)
                home.helper = SqliteHelper(this, "article", null, 1)
                home.arguments = bundle

                home.setArticle()

                Log.e("writeActivity.send", "data sending end")
                }

      finish()

  }

}
// actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
        finish()
        true
        }
        else -> {

        // Invoke the superclass to handle it.
        super.onOptionsItemSelected(item)
        }
    }



fun requirePermissions(permissions: Array<String>, requestCode: Int) {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
          permissionGranted(requestCode)
          } else {
          // isAllPermissionsGranted : 권한이 모두 승인 되었는지 여부 저장
          // all 메서드를 사용하면 배열 속에 들어 있는 모든 값을 체크할 수 있다.
          val isAllPermissionsGranted =
              permissions.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
          if (isAllPermissionsGranted) {
              permissionGranted(requestCode)
          } else {
              // 사용자에 권한 승인 요청
              ActivityCompat.requestPermissions(this, permissions, requestCode)
          }
    }
}


    private fun permissionGranted(requestCode: Int) { //갤러리 접근 허용됐다면 갤러리 오픈
        when (requestCode) {
          PERMISSION_Album -> openGallery()
        }
    }

    fun openGallery() { //갤러리 열기
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, 1 )

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //앨범에서 이미지 가져온 후 작업
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
          when (requestCode) {
              1 -> {
                  data?.data?.let { uri ->
                      val realpath = RealPathUtil()
                      var imagePath: String?= realpath.getRealPathFromURI_API19(this, uri)
                      f= File(imagePath)
                      Glide.with(this).load(uri).into(binding.imgBtn)
                  }
              }
          }
        }
    }
    fun uploadImg(){ //aws s3에 이미지 업로드
        val credentialsProvider: CognitoCachingCredentialsProvider
        val s3: AmazonS3
        val transferUtility: TransferUtility

        credentialsProvider = CognitoCachingCredentialsProvider(
            this,
            "ap-northeast-2:e0ea2090-8c8e-4b43-a765-348b4fb30098",  // Identity Pool ID
            Regions.AP_NORTHEAST_2 // Region
        )
        s3 = AmazonS3Client(credentialsProvider)
        s3.setRegion(Region.getRegion(Regions.AP_NORTHEAST_2))
        s3.setEndpoint("s3.ap-northeast-2.amazonaws.com")

        transferUtility = TransferUtility(s3, this)
        val observer: TransferObserver = transferUtility.upload(
            "fofuploadtest",
            key, f
        )


    }



}