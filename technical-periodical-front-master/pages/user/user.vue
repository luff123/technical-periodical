<template>
	<view class="page">
		<view class="user" @click="toLogin()">
			<image :src="head_src" class="head"></image>
			<view class="user-info">
				<text>{{ userInfo.name }}</text>
				<br>
				<text>ID : {{ userInfo.id }}</text>
			</view>
		</view>
		<view class="other-info">
			<view @click="toMyStar()">
				<image src="../../static/icon/star.png"></image>
				我的收藏
			</view>
			<view @click="toMyPub()">
				<image src="../../static/icon/pub.png"></image>
				我的订阅</view>
<!-- 			<view>
				<image src="../../static/icon/history.png"></image>
				离线缓存
			</view> -->
			<view @click="toTutorial()">
				<image src="../../static/icon/tutorial.png"></image>
				新手教程
			</view>
			<view @click="getPosition()">
				<image src="../../static/icon/search.png"></image>
				当前位置
			</view>
			<view @click="toMySettings()">
				<image src="../../static/icon/set.png"></image>
				系统设置
			</view>
		</view>
<!-- 		<view class="self">
			<view @click="">
				<image src="../../static/icon/my_perio.png"></image>
				我的期刊
			</view>
			<view @click="">
				<image src="../../static/icon/my_paper.png"></image>
				我的文章
			</view>
		</view> -->
	</view>
</template>

<script>
	export default {
		onShow() {
			this.prefix = this.contextPath;
			this.userInfo = getApp().globalData.user;
		},
		data() {
			return {
				"userInfo" : {},
				"prefix": "",
				"head_src": "../../static/head_resource/1.jpg"
			}
		},
		methods: {
			getPosition() {
				console.log('get location');
				uni.getLocation({
					success: (res) => {
						var latitude = res.latitude;
						var longitude = res.longitude;
						var address = res.address;
						uni.showModal({
							content: '纬度：' + latitude + '\n经度：' + longitude,
							showCancel: false
						});
						console.log(res);
						console.log(res.address);
					},
					fail: (res) => {
						console.log(res);
					}
				});
			},
			toLogin() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				} else {
					;
				}
			},
			toMyStar() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				} else {
					uni.request({
						url: this.contextPath + "/papers?message=star&" + "id=" + getApp().globalData.user.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							getApp().globalData.papers = res.data;
							uni.navigateTo({
								url: "../papers/papers"
							});
						}
					});
				}
			},
			toMyPub() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				} else {
					uni.request({
						url: this.contextPath + "/periodicals?id=" + getApp().globalData.user.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							getApp().globalData.periodicals = res.data;
							uni.navigateTo({
								url: "../periodicals/periodicals"
							});
						}
					});
				}
			},
			toMySettings() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				} else {
					uni.navigateTo({
						url: "../setting/setting"
					});
				}
			},
			toMyPeriodicals() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				} else {
					uni.request({
						url: this.contextPath + "/periodicals?authorId=" + getApp().globalData.user.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							getApp().globalData.periodicals = res.data;
							uni.navigateTo({
								url: "../periodicals/periodicals"
							});
						}
					});
				}
			},
			toMyPapers() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				} else {
					uni.request({
						url: this.contextPath + "/papers?authorId=" + getApp().globalData.user.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							getApp().globalData.papers = res.data;
							uni.navigateTo({
								url: "../papers/papers"
							});
						}
					});
				}
			},
			toTutorial() {
				uni.navigateTo({
					url: "../tutorial/tutorial"
				});
			}
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #F1F1F1;
		/* position: absolute; */
		display: flex;
		flex-direction: column;
		flex-wrap: wrap;
		align-items: center;
	}
	.user {
		width: 90%;
		height: 250rpx;
		background-color: #ffffff;
		display: flex;
		flex-direction: column;
		margin-top: 130rpx;
		border-radius: 30rpx;
		justify-content: center;
		align-items: center;
		text-align: center;
		box-shadow: 1rpx 1rpx 1rpx #555555;
	}
	.head {
		width: 200rpx;
		height: 200rpx;
		border-radius: 100rpx;
		margin-top: -150rpx;
		border: 1rpx solid #808080;
		margin-bottom: 15rpx;
	}
	.user-info {
	}
	.user-info text:nth-child(1) {
		font-size: 50rpx;
		font-weight: bold;
	}
	.user-info text:nth-child(3) {
		font-size: 30rpx;
	}
	.other-info {
		width: 90%;
		/* height: 400rpx; */
		background-color: #ffffff;
		display: flex;
		flex-direction: column;
		margin-top: 30rpx;
		border-radius: 30rpx;
		justify-content: center;
		align-items: center;
		text-align: center;
		box-shadow: 1rpx 1rpx 1rpx #555555;
	}
	.other-info view {
		font-size: 40rpx;
		letter-spacing: 10rpx;
		height: 80rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin: 5px 10px;
		border-radius: 20rpx;
		/* border: 1rpx solid #999999; */
		padding: 0 10rpx;
		align-items: center;
	}
	.other-info view image {
		width: 60rpx;
		height: 60rpx;
	}
	
	.self {
		width: 90%;
		height: 200rpx;
		background-color: #ffffff;
		display: flex;
		flex-direction: column;
		margin-top: 30rpx;
		border-radius: 30rpx;
		justify-content: center;
		align-items: center;
		text-align: center;
		box-shadow: 1rpx 1rpx 1rpx #555555;
	}
	.self view {
		font-size: 40rpx;
		letter-spacing: 10rpx;
		height: 80rpx;
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin: 5px 10px;
		border-radius: 20rpx;
		/* border: 1rpx solid #999999; */
		padding: 0 10rpx;
		align-items: center;
	}
	.self view image {
		width: 60rpx;
		height: 60rpx;
	}
</style>
