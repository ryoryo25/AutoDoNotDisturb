package com.ryoryo.autodonotdisturb.selectedapps

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ryoryo.autodonotdisturb.R
import com.ryoryo.autodonotdisturb.data.AppInfo
import com.ryoryo.autodonotdisturb.databinding.FragmentSelectedAppsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SelectedApps : Fragment() {

    private var _binding: FragmentSelectedAppsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedAppsBinding.inflate(inflater, container, false)

        val pm = this.requireActivity().packageManager
        val flags = PackageManager.MATCH_UNINSTALLED_PACKAGES or PackageManager.MATCH_DISABLED_COMPONENTS
        val installedAppsList = pm.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(flags.toLong()))
        val appInfoList = mutableListOf<AppInfo>()
        for (app in installedAppsList) {
            appInfoList.add(
                AppInfo(
                    isSelected = false,
                    name = app.loadLabel(pm).toString(),
                    icon = app.loadIcon(pm),
                    packageName = app.packageName
                )
            )
        }

        val selectedAppsAdapter = SelectedAppsAdapter(appInfoList)
        val recyclerView = binding.selectedApps
        recyclerView.adapter = selectedAppsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectApp.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}