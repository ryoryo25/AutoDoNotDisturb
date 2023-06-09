package com.ryoryo.autodonotdisturb.installedapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryoryo.autodonotdisturb.R
import com.ryoryo.autodonotdisturb.data.AppInfo

class InstalledAppsAdapter(private var dataSet: MutableList<AppInfo>):
    RecyclerView.Adapter<InstalledAppsAdapter.InstalledAppsViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class InstalledAppsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appIcon: ImageView
        val appName: TextView
        val appSystemName: TextView
        val selectCheck: CheckBox

        init {
            // Define click listener for the ViewHolder's View.
            appIcon = view.findViewById(R.id.appIcon)
            appName = view.findViewById(R.id.appName)
            appSystemName = view.findViewById(R.id.appSystemName)
            selectCheck = view.findViewById(R.id.selectCheck)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): InstalledAppsViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.installed_apps_item, viewGroup, false)

        return InstalledAppsViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: InstalledAppsViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.appIcon.setImageDrawable(dataSet[position].icon)
        viewHolder.appName.text = dataSet[position].name
        viewHolder.appSystemName.text = dataSet[position].packageName
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

}