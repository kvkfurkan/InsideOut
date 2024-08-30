package dev.furkankavak.insideout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dev.furkankavak.insideout.databinding.FragmentGivingBinding


class GivingFragment : Fragment() {

    private var _binding: FragmentGivingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGivingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = binding.gifImageView
        val gifUrl = "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExZWtrY3hjYmFqb3Jid3ZldGFyYTZmdjZmeDNveWR0bm1zMDdsZDUxZSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/nI1KDpXAN8MQxEKdIX/giphy.webp"

        Glide.with(this)
            .load(gifUrl)
            .into(imageView)

    }
}