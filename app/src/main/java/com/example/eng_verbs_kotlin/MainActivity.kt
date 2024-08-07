package com.example.eng_verbs_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv_first = findViewById<TextView>(R.id.tv_first)
        var tv_last = findViewById<TextView>(R.id.tv_last)
        var btn_voice_verb = findViewById<TextView>(R.id.btn_voice_verb)
        var tv_verb = findViewById<TextView>(R.id.tv_verb)
        var btn_voice_sentence = findViewById<TextView>(R.id.btn_voice_sentence)
        var tv_sentence = findViewById<TextView>(R.id.tv_sentence)
        var btn_previous = findViewById<TextView>(R.id.btn_previous)
        var btn_next = findViewById<TextView>(R.id.btn_next)
        var Ttos:TextToSpeech?=null
        var res:Int?=null

        // Initial
        var index = 0
        var num = resources.getStringArray(R.array.num)
        var verbs = resources.getStringArray(R.array.verb)
        var sentences = resources.getStringArray(R.array.sentence)
        tv_first.text = num[index].toString()
        tv_last.text = num.size.toString()
        tv_verb.text = verbs[index]
        tv_sentence.text = sentences[index]
        Ttos = TextToSpeech(this, TextToSpeech.OnInitListener {
            var res = Ttos!!.setLanguage(Locale.ENGLISH)
        })

        // Next
        btn_next.setOnClickListener {
            if(index<verbs.size-1){
                index = index + 1
                tv_first.text = num[index].toString()
                tv_verb.text = verbs[index]
                tv_sentence.text = sentences[index]
            }
        }

        //Previous
        btn_previous.setOnClickListener {
            if(index>0){
                index = index - 1
                tv_first.text = num[index].toString()
                tv_verb.text = verbs[index]
                tv_sentence.text = sentences[index]
            }
        }

        btn_voice_verb.setOnClickListener {
            if(res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }else{
                Ttos!!.speak(tv_verb.text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }

        btn_voice_sentence.setOnClickListener {
            if(res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(this, "Error",Toast.LENGTH_LONG).show()
            }else{
                Ttos!!.speak(tv_sentence.text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }
}