package dev.furkankavak.insideout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dev.furkankavak.insideout.databinding.FragmentOptimismBinding

class OptimismFragment : Fragment() {

    private var _binding: FragmentOptimismBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptimismBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = binding.gifImageView
        val gifUrl = "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExcDc1MGQxaWRtc3IycWRsc3BuMWV4bXAxZGVyMjhmamx2OWFjanhiOSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/11tTNkNy1SdXGg/giphy.webp"

        Glide.with(this)
            .load(gifUrl)
            .into(imageView)
    }
}