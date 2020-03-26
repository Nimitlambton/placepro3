package com.example.labtest1.feeskeeper.myplaces3


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.feeViewModel
import com.example.labtest1.feeskeeper.myplaces3.Dbconfig.mylocation

private lateinit var wordViewModel: feeViewModel


class MainActivity : AppCompatActivity() , onfeeclick {




    private val newWordActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = FeeListAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(feeViewModel::class.java)

        wordViewModel.allfee.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                adapter.setWords(it)

            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {


        // Inflate the menu; this adds items to the action bar if it is
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add) {
            ShowaddDetails()
        }

        if (item.itemId == R.id.map) {


            showmap()
        }




        return super.onOptionsItemSelected(item)
    }

    private fun showmap() {

        val intent = Intent(this@MainActivity, MapsActivity::class.java)

        startActivityForResult(intent, newWordActivityRequestCode)
    }

    private fun ShowaddDetails() {


        val intent = Intent(this@MainActivity, addAct::class.java)

        startActivityForResult(intent, newWordActivityRequestCode)
    }




    override fun onitemclick(item: mylocation, position: Int) {


        Toast.makeText(this,item.title1+ "deleted", Toast.LENGTH_SHORT).show()

        wordViewModel.delete(item.loction_Id2)







    }




}
