package dev.furkankavak.insideout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dev.furkankavak.insideout.databinding.FragmentHappinessBinding

class HappinessFragment : Fragment() {

    private var _binding: FragmentHappinessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHappinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = binding.gifImageView
        val gifUrl = "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExemoyc3pudnQzemZuaWZnajYyd212eTAzbnpqNG04MmM0aDdoMTg3ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/nWSs8uXjqRvpu/giphy.webp"

        Glide.with(this)
            .load(gifUrl)
            .into(imageView)
    }
}