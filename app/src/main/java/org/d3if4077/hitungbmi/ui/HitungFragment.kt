package org.d3if4077.hitungbmi.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.d3if4077.hitungbmi.R
import org.d3if4077.hitungbmi.data.KategoriBmi
import org.d3if4077.hitungbmi.databinding.FragmentHitungBinding

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private lateinit var kategoriBmi: KategoriBmi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(
                layoutInflater, container, false)
        binding.root.setOnClickListener { hitungBmi () }
        binding.shareButton.setOnClickListener { shareData() }
        setHasOptionsMenu(true)
        return binding.root

     }
    private fun hitungBmi() {
       // binding.bmiTextView.text = getString(R.string.bmi_x,bmi)
       // binding.kategoriTextView.text = getString(R.string.kategori_x, kategori)
       // binding.buttonGroup.visibility = View.VISIBLE
    }
    private fun shareData() {
        //val selectedId = binding.radioGroup.checkedRadioButtonId
       // val gender = if (selectedId == R.id.priaRadioButton)
            getString(R.string.pria)
        //else
            getString(R.string.wanita)

        val shareIntent = Intent(Intent.ACTION_SEND)
        //shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getKategori(bmi: Float, isMale: Boolean): String {
        kategoriBmi = if (isMale) {
            when{
               // bmi < 20.5 -> kategoriBmi.KURUS
                //bmi >= 27.0 -> kategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }

        }
        else {
            when {
                bmi < 18.5 -> KategoriBmi.KURUS
                bmi >= 25.0 -> KategoriBmi.GEMUK
                else -> KategoriBmi.IDEAL
            }
        }
        val stringRes = when (kategoriBmi) {
            KategoriBmi.KURUS -> R.string.kurus
            KategoriBmi.IDEAL -> R.string.ideal
            KategoriBmi.GEMUK -> R.string.gemuk
        }
        return getString(stringRes)
    }
}





