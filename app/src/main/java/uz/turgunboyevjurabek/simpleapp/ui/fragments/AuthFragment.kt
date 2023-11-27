package uz.turgunboyevjurabek.simpleapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import uz.turgunboyevjurabek.simpleapp.R
import uz.turgunboyevjurabek.simpleapp.databinding.FragmentAuthBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthFragment : Fragment() {
    private val binding by lazy { FragmentAuthBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
        if (binding.edtMasc.text?.isNotEmpty() == true && binding.edtMasc.text!!.length==19){
            findNavController().navigate(R.id.verificationFragment)
            }
            Toast.makeText(requireContext(), "${binding.edtMasc.text!!.length}", Toast.LENGTH_SHORT).show()
        }
        edtWork()
    }
    @SuppressLint("ResourceAsColor")
    private fun edtWork(){
        val pinView=binding.edtMasc
        binding.edtMasc.addTextChangedListener {
            pinView.setBackgroundResource(R.drawable.shape_edt)
            binding.btnContinue.setBackgroundColor(resources.getColor(R.color.border_view2))
            if(pinView.text?.isEmpty()==true){
                pinView.setBackgroundResource(R.drawable.shape_edt2)
                binding.btnContinue.setBackgroundColor(resources.getColor(R.color.enabled_false))
            }

        }

    }
}