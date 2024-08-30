package dev.furkankavak.insideout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dev.furkankavak.insideout.databinding.FragmentRespectBinding


class RespectFragment : Fragment() {

    private var _binding: FragmentRespectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRespectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = binding.gifImageView
        val gifUrl = "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExM3luazIybDNhdGlnbG83NHhqY3AyY29sanA0dm9nNWsyOWg0YXhsNyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/1X7qyp2SVqX8A/giphy.webp"

        Glide.with(this)
            .load(gifUrl)
            .into(imageView)
    }
}