/*
 * Copyright 2018 Priyank Vasa
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pvryan.mobilecodingchallenge.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pvryan.mobilecodingchallenge.Constants
import com.pvryan.mobilecodingchallenge.R
import com.pvryan.mobilecodingchallenge.data.Image
import com.pvryan.mobilecodingchallenge.ui.ExpandedImageActivity
import com.pvryan.mobilecodingchallenge.ui.GalleryActivity
import kotlinx.android.synthetic.main.item_image.view.*

@Suppress("MemberVisibilityCanBePrivate")
class GalleryAdapter(private val context: Context, val images: List<Image>) :
        RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GalleryViewHolder {

        val imageView = LayoutInflater.from(context)
                .inflate(R.layout.item_image, parent, false)
        return GalleryViewHolder(imageView)
    }
    
    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val image = images[position]

        Glide.with(context)
                .asBitmap()
                .load(Uri.parse(image.urls.regular))
                .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.imageView.context, ExpandedImageActivity::class.java)
            val args = Bundle()
            args.putParcelableArrayList(Constants.keyImages, ArrayList(images))
            args.putInt(Constants.keyPosition, position)
            intent.putExtras(args)
            (context as GalleryActivity).startActivityForResult(
                    intent, Constants.rcExpandedImageActivity)
        }
    }

    class GalleryViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.ivImage as ImageView
    }
}