package dev.furkankavak.insideout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dev.furkankavak.insideout.databinding.FragmentKindnessBinding

class KindnessFragment : Fragment() {

    private var _binding: FragmentKindnessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKindnessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = binding.gifImageView
        val gifUrl = "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExM2Z0dmJ5bjgwdzNsb3g1c3l2NGd2dTB3ZzRpeTZ1ZXB2b2U0aTh3eSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/t9l03qknf0alkuE5fi/giphy.webp"

        Glide.with(this)
            .load(gifUrl)
            .into(imageView)
    }
}