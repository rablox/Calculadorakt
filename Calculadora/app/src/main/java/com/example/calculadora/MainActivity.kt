package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.calculadora.model.Calculadora
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var n1: Int = 0
    private var n2: Int = 0
    private var actual: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            binding.textView.text = savedInstanceState.getString("TEXTVIEW", "")
            n1 = savedInstanceState.getInt("N1", 0)
            n2 = savedInstanceState.getInt("N2", 0)
            actual = savedInstanceState.getSerializable("ACTUAL") as Char?
        }

        instancias()
        acciones()
    }



    private fun instancias() {
    }

    private fun acciones() {
        binding.boton0.setOnClickListener(this)
        binding.boton1.setOnClickListener(this)
        binding.boton2.setOnClickListener(this)
        binding.boton3.setOnClickListener(this)
        binding.boton4.setOnClickListener(this)
        binding.boton5.setOnClickListener(this)
        binding.boton6.setOnClickListener(this)
        binding.boton7.setOnClickListener(this)
        binding.boton8.setOnClickListener(this)
        binding.boton9.setOnClickListener(this)
        binding.botonAc?.setOnClickListener(this)
        binding.botonMasMenos?.setOnClickListener(this)
        binding.botonPorcentaje.setOnClickListener(this)
        binding.botonDiv?.setOnClickListener(this)
        binding.botonMulti?.setOnClickListener(this)
        binding.botonResta.setOnClickListener(this)
        binding.botonSuma?.setOnClickListener(this)
        binding.botonIgual.setOnClickListener(this)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("TEXTVIEW", binding.textView.text.toString())
        outState.putInt("N1", n1)
        outState.putInt("N2", n2)
        actual?.let {
            outState.putChar("ACTUAL", it)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.botonAc -> limpiar()
            R.id.botonMasMenos -> masMenos()
            R.id.botonPorcentaje -> porcentaje()
            R.id.boton0 -> appendNumber(0)
            R.id.boton1 -> appendNumber(1)
            R.id.boton2 -> appendNumber(2)
            R.id.boton3 -> appendNumber(3)
            R.id.boton4 -> appendNumber(4)
            R.id.boton5 -> appendNumber(5)
            R.id.boton6 -> appendNumber(6)
            R.id.boton7 -> appendNumber(7)
            R.id.boton8 -> appendNumber(8)
            R.id.boton9 -> appendNumber(9)
            R.id.botonSuma -> operando('+')
            R.id.botonResta -> operando('-')
            R.id.botonMulti -> operando('*')
            R.id.botonDiv -> operando('/')

            R.id.botonIgual -> resultado()
        }
    }

    private fun appendNumber(number: Int) {
        binding.textView.text = "${binding.textView.text}$number"
    }

    private fun limpiar() {
        binding.textView.text = ""
        n1 = 0
        n2 = 0
        actual = null
    }

    private fun masMenos() {
        val numeroActual = binding.textView.text.toString().toIntOrNull() ?: return
        binding.textView.text = (numeroActual * -1).toString()
    }

    private fun porcentaje() {
        val numeroActual = binding.textView.text.toString().toIntOrNull() ?: return
        binding.textView.text = (numeroActual / 100).toString()
    }

    private fun operando(op: Char) {
        n1 = binding.textView.text.toString().toIntOrNull() ?: return
        actual = op
        binding.textView.text = ""
    }

    private fun resultado() {
        n2 = binding.textView.text.toString().toIntOrNull() ?: return
        actual?.let {
            val resultado = Calculadora.calcular(n1, n2, it)
            binding.textView.text = resultado.toString()
            n1 = resultado
        }
    }
}
