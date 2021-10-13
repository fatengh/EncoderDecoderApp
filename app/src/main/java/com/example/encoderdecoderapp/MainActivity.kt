package com.example.encoderdecoderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var etEnc: EditText
    private lateinit var etDec: EditText
    private lateinit var btnEnc: Button
    private lateinit var btnDec: Button
    private lateinit var rv: RecyclerView
    private lateinit var rvAdap: RVAdap
    private lateinit var inputs: ArrayList<Phrase>
    private val alphabets = "abcdefghijklmnopqrstuvwxyz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputs = arrayListOf()
        rv = findViewById(R.id.rv)
        etEnc = findViewById(R.id.etEnc)
        etDec = findViewById(R.id.etDec)
        btnEnc = findViewById(R.id.btnEnc)
        btnDec = findViewById(R.id.btnDec)

        rvAdap = RVAdap(inputs)
        rv.adapter = rvAdap
        rv.layoutManager = LinearLayoutManager(this)

        btnEnc.setOnClickListener { processText(false) }
        btnDec.setOnClickListener { processText(true) }
    }

    private fun processText(encode: Boolean){
        var outputs = ""
        var i = 0
        if(!encode){
            if(!etEnc.text.isNullOrEmpty()){
                for(letter in etEnc.text.toString()){
                    if(alphabets.indexOf(letter) < 0){
                        if(alphabets.uppercase().indexOf(letter) < 0){
                            outputs += letter
                        }else{
                            i = alphabets.uppercase().indexOf(letter) + 13
                            if(i > 25){
                                i -= 26
                            }
                            outputs += alphabets.uppercase()[i]
                        }
                    }else{
                        i = alphabets.indexOf(letter) + 13
                        if(i > 25){
                            i -= 26
                        }
                        outputs += alphabets[i]
                    }
                }
                inputs.add(Phrase(etEnc.text.toString(), true))
                etEnc.text.clear()
            }else{
                Toast.makeText(this, "please enter somthing", Toast.LENGTH_LONG).show()
            }
        }else{
            if(!etDec.text.isNullOrEmpty()){
                for(letter in etDec.text.toString()){
                    if(alphabets.indexOf(letter) < 0){
                        if(alphabets.uppercase().indexOf(letter) < 0){
                            outputs += letter
                        }else{
                            i = alphabets.uppercase().indexOf(letter) - 13
                            if(i < 0){
                                i += 26
                            }
                            outputs += alphabets.uppercase()[i]
                        }
                    }else{
                        i = alphabets.indexOf(letter) - 13
                        if(i < 0){
                            i += 26
                        }
                        outputs += alphabets[i]
                    }
                }
                inputs.add(Phrase(etDec.text.toString(), true))
                etDec.text.clear()
            }else{
                Toast.makeText(this, "Phrase cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
        inputs.add(Phrase(outputs, false))
        rvAdap.notifyDataSetChanged()
    }
}