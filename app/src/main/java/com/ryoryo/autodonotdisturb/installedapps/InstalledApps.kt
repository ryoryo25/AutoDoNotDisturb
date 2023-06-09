package com.ryoryo.autodonotdisturb.installedapps

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ryoryo.autodonotdisturb.R
import com.ryoryo.autodonotdisturb.data.AppInfo
import com.ryoryo.autodonotdisturb.databinding.FragmentInstalledAppsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class InstalledApps : Fragment() {

    private var _binding: FragmentInstalledAppsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstalledAppsBinding.inflate(inflater, container, false)

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

        val installedAppsAdapter = InstalledAppsAdapter(appInfoList)
        val recyclerView = binding.installedApps
        recyclerView.adapter = installedAppsAdapter

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addApp.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}