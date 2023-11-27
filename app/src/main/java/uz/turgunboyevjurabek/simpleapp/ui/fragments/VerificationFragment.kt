package uz.turgunboyevjurabek.simpleapp.ui.fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import uz.turgunboyevjurabek.simpleapp.R
import uz.turgunboyevjurabek.simpleapp.databinding.FragmentVerificationBinding
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerificationFragment : Fragment() {
    private val binding by lazy { FragmentVerificationBinding.inflate(layoutInflater)}
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var auth: FirebaseAuth
    lateinit var storedVerificationId:String
    lateinit var resentToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment



        return binding.root
    }

    private fun sendVerificationCode(number:String){
        val option=PhoneAuthOptions.newBuilder()
            .setActivity(requireActivity())
            .setPhoneNumber(number)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(option)

    }

    private val callback=object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted: Uraaa")
          //  Toast.makeText(this@MainActivity2, "callback", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(p0: FirebaseException) {
          //  Toast.makeText(this@MainActivity2, "No callback ${p0.message}", Toast.LENGTH_LONG).show()
            Log.d(TAG, "onVerificationCompleted:Failed",p0)
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            Log.d(TAG, "onCodeSent: Kod jo'natilidi")
            storedVerificationId=p0
            resentToken=p1
        }
    }
    private fun verifyCode(){
        val code=binding.etVerificationCode.text.toString()
        if (code.length==6){
            binding.etVerificationCode.clearFocus()
            val credential=PhoneAuthProvider.getCredential(storedVerificationId,code)
            signInWithPhoneAuthCredential(credential)
        }
    }


    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                } else {
                    Toast.makeText(requireContext(), "Mufaqiyatsiz", Toast.LENGTH_SHORT).show()
                    if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            requireContext(),
                            "Kod hato kiritildi tekshirib qayta kiriting",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()

        val number=arguments?.getString("key_number")
        edtWork()
        sendVerificationCode(number.toString())


        binding.etVerificationCode.addTextChangedListener {
            verifyCode()
        }


    }
    @SuppressLint("ResourceAsColor")
    private fun edtWork(){
        binding.etVerificationCode.setAnimationEnable(true)
        val pinView=binding.etVerificationCode
        binding.etVerificationCode.addTextChangedListener {

            // Agar pinView bo'sh bo'lsa, lineColor rangini o'zgartirish
            if(pinView.text.isNullOrEmpty()){
                pinView.setLineColor(resources.getColor(R.color.border_view))
                binding.btnContinue.setBackgroundColor(resources.getColor(R.color.enabled_false))
            }else {
                // Aks holda, yozilgan paytda lineColor rangini o'zgartirish
                pinView.setLineColor(resources.getColor(R.color.border_view2))
                binding.btnContinue.setBackgroundColor(resources.getColor(R.color.border_view2))
            }

        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VerificationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}