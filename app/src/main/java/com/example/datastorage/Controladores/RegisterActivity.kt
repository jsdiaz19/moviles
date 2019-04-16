package com.example.datastorage.Controladores

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.UserDBServices
import com.example.datastorage.Servicios.BitMapServices
import kotlinx.android.synthetic.main.activity_register.view.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var image: ByteArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun profile(view: View)
    {
        val nombre = findViewById<TextView>(R.id.nombre);
        val correo = findViewById<TextView>(R.id.correo);
        val contra = findViewById<TextView>(R.id.contraseña);
        val edad = findViewById<TextView>(R.id.edad);

        if(TextUtils.isEmpty(nombre.text.toString())==false  &&  TextUtils.isEmpty(correo.text.toString())==false   && TextUtils.isEmpty(contra.text.toString())==false  && TextUtils.isEmpty(edad.text.toString())==false )
        {
            val user = User(null, nombre.text.toString(), correo.text.toString(),edad.text.toString().toInt(), contra.text.toString(),this.image);
            if( !UserDBServices(this).verifyUser(user)){
                nombre.text = ""
                contra.text = ""
                correo.text = ""
                edad.text = ""
                UserDBServices(this).saveUser(user)
                Toast.makeText(this, "Se ha Guardado Un Usuario", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "El usuario ya existe, por favor intentelo de nuevo", Toast.LENGTH_SHORT).show()
            }

           /* val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent);*/ //
        }else{
            Toast.makeText(this, "Faltan Campos por llenar correctamente",  Toast.LENGTH_SHORT).show()
        }

    }

    fun Cargar(view: View){
        cargarImagen()
    }

    private fun cargarImagen() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/");
        intent.putExtra("crop", "true")
        intent.putExtra("outputX", 100)
        intent.putExtra("outputY", 100)
        intent.putExtra("scale", true)
        intent.putExtra("return-data", true)
        startActivityForResult(intent,1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode== 1 && data!=null){
            val imageUri= data?.data
            val bitmap= MediaStore.Images.Media.getBitmap(this.contentResolver,imageUri)
            this.image= BitMapServices(this).getBytes(bitmap)

        }
    }
    //juancard190@gmail.com   123456
    //juanmiok@hotmail.es     2468
    fun volver(view:View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }

}

