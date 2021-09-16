简介：放微博 横排头像展示控件
使用：在xml中设置CoverView布局
使用方法：
            CoverAdapter<UserEntity> coverAdapter = new CoverAdapter<UserEntity>() {
                @Override
                public void onDisplayImage(Context context, ImageView imageView, UserEntity s) {
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.mipmap.ic_logo)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE); //缓存
                    Glide.with(ChannelDetailListActivity.this).load(s.getAvatar()).apply(options).into(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OtherDetailActivity.actionStart(ChannelDetailListActivity.this, s.getUid());
                        }
                    });
                }
            };
            coverView.setAdapter(coverAdapter);
            coverView.setData(obj);