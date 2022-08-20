<template>
	<view class="page">
<!-- 		<view class="change-head">
			<text>修改头像</text>
			<hr>
			<button @click="changeHead()">点击修改</button>
		</view> -->
		<view class="change-name">
			<text>修改昵称</text>
			<hr>
			<view class="change-name-ch">
				<input type="text" maxlength="25" v-model="name" required/>
				<button @click="changeName()">修改</button>
			</view>
		</view>
		<view class="clear-history">
			<text>清空历史</text>
			<hr>
			<button>点击清空</button>
		</view>
		<view class="change-password">
			<text>修改密码</text>
			<hr>
			<view class="origin-password">
				<text>原密码</text>
				<input type="password" placeholder="请输入原密码" v-model="originPassword" required/>
			</view>
			<view class="origin-password">
				<text>新密码</text>
				<input type="password" placeholder="请输入新密码" v-model="newPassword" required/>
			</view>
			<view class="origin-password">
				<text>新密码</text>
				<input type="password" placeholder="请确认新密码" v-model="rePassword" required/>
			</view>
			<button @click="changePassword()">确认修改</button>
		</view>
		<view class="logout">
			<text>退出登录</text>
			<hr>
			<button @click="logout()">登出</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"name": "",
				"originPassword": "",
				"newPassword": "",
				"rePassword": ""
			}
		},
		methods: {
			changeHead() {
				uni.navigateTo({
					url: "../heads/heads"
				});
			},
			changeName() {
				var oldName = getApp().globalData.user.name;
				var newName = this.name;
				if (oldName == newName) {
					uni.showModal({
						content: '用户名未修改',
						showCancel: false
					});
				}
				else if (newName.length == 0) {
					uni.showModal({
						content: '用户名过短',
						showCancel: false
					});
				}
				else {
					uni.request({
						url: this.contextPath + "/change_name?id=" + getApp().globalData.user.id + "&name=" + newName,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							getApp().globalData.user.name = newName;
							uni.navigateTo({
								url: "../user/user"
							});
						}
					});
				}
			},
			changePassword() {
				var _originPassword = this.originPassword;
				var _newPassword = this.newPassword;
				var _rePassword = this.rePassword;
				var _userId = getApp().globalData.user.id;
				if (_newPassword != _rePassword) {
					console.log("重复密码错误！");
					uni.showModal({
						content: '重复密码错误！',
						showCancel: false
					});
				} else if (_originPassword == _newPassword) {
					console.log("新旧密码相同");
					uni.showModal({
						content: '新旧密码相同',
						showCancel: false
					});
				} else {
					uni.request({
						url: this.contextPath + "/change_password?id=" + _userId + "&originalPassword=" + _originPassword + "&newPassword=" + _rePassword,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							if (res.data == "error") {
								console.log("原密码错误！");
								uni.showModal({
									content: '原密码错误！',
									showCancel: false
								});
							} else {
								console.log("修改成功，请重新登录！");
								uni.showModal({
									content: '修改成功，请重新登录！',
									showCancel: false
								});
								getApp().globalData.user.id = "0";
								getApp().globalData.user.name = "未登录";
								uni.navigateTo({
									url: "../user/user"
								});
							}
						}
					});
				}
			},
			logout() {
				getApp().globalData.user.id = "0";
				getApp().globalData.user.name = "未登录";
				uni.navigateBack();
			}
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #f7f7f7;
		/* position: absolute; */
		display: flex;
		flex-direction: column;
	}
	.change-head {
		margin-top: 20rpx;
	}
	.change-head text {
		font-size: 40rpx;
		font-weight: bold;
	}
	.change-head hr {
		background-color: #444444;
	}
	.change-head button {
		margin: 10rpx 20rpx;
		background-color: #C8C7CC;
	}
	
	.change-name {
		margin-top: 20rpx;
	}
	.change-name text {
		font-size: 40rpx;
		font-weight: bold;
	}
	.change-name hr {
		background-color: #444444;
	}
	.change-name-ch {
		display: flex;
		flex-direction: row;
		/* background-color: #4CD964; */
		align-items: center;
		height: 100rpx;
	}
	.change-name-ch input {
		/* background-color: #F1F1F1; */
		font-size: 35rpx;
		margin-left: 20rpx;
		padding: 10rpx 20rpx;
		border: 1rpx solid #555555;
		width: 70%;
		border-radius: 20rpx 0 0 20rpx;
	}
	.change-name-ch button {
		margin: 0;
		height: 70rpx;
		align-items: center;
		display: flex;
		border-radius: 0 20rpx 20rpx 0;
		background-color: #007AFF;
		color: #333333;
	}

	.clear-history {
		margin-top: 20rpx;
	}
	.clear-history text {
		font-size: 40rpx;
		font-weight: bold;
	}
	.clear-history hr {
		background-color: #444444;
	}
	.clear-history button {
		margin: 10rpx 20rpx;
		background-color: #C8C7CC;
	}
	
	.change-password {
		margin-top: 20rpx;
	}
	.change-password>text {
		font-size: 40rpx;
		font-weight: bold;
	}
	.change-password hr {
		background-color: #444444;
	}
	.change-password button {
		margin: 10rpx 20rpx;
		background-color: #C8C7CC;
	}
	.origin-password {
		display: flex;
		flex-direction: row;
		/* background-color: #C8C7CC; */
		margin: 15rpx 20rpx;
	}
	.origin-password text {
		background-color: #007AFF;
		font-size: 35rpx;
		padding: 10rpx 20rpx;
		border-radius: 15rpx 0 0 15rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	.origin-password input {
		border: 1rpx solid #333333;
		width: 77%;
		font-size: 40rpx;
		padding: 8rpx 10rpx;
		border-radius: 0 20rpx 20rpx 0;
	}
	
	.logout {
		margin-top: 20rpx;
	}
	.logout text {
		font-size: 40rpx;
		font-weight: bold;
	}
	.logout hr {
		background-color: #444444;
	}
	.logout button {
		margin: 10rpx 20rpx;
		background-color: #C8C7CC;
	}
</style>
