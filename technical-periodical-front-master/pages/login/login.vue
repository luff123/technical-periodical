<template>
	<view class="page">
		
		<view class="login" v-show="isLogin">
			<text>登 录</text>
			<view class="input-box">
				<image src="../../static/icon/phone.png"></image>
				<input type="text" maxlength="11" placeholder="请输入手机号" v-model="loginUserId" required/>
			</view>
			<view class="input-box">
				<image src="../../static/icon/password.png"></image>
				<input type="password" maxlength="30" placeholder="请输入密码" v-model="loginPassword" required/>
			</view>
			<button @click="login()">登 录</button>
			<button @click="changeBox()">注册</button>
			<button>忘记密码</button>
		</view>
		
		<view class="register" v-show="!isLogin">
			<text>注 册</text>
			<view class="input-box">
				<image src="../../static/icon/phone.png"></image>
				<input type="text" maxlength="11" placeholder="请输入手机号" v-model="registerUserId" required/>
			</view>
			<view class="input-box">
				<image src="../../static/icon/name.png"></image>
				<input type="text" maxlength="25" placeholder="请输入用户名" v-model="registerName" required/>
			</view>
			<view class="input-box">
				<image src="../../static/icon/password.png"></image>
				<input type="password" maxlength="30" placeholder="请输入密码" v-model="registerPassword" required/>
			</view>
			<view class="input-box">
				<image src="../../static/icon/password.png"></image>
				<input type="password" maxlength="30" placeholder="请重复密码" v-model="registerRePassword" required/>
			</view>
			<button @click="register()">注 册</button>
			<button @click="changeBox()">登录</button>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"isLogin" : true,
				"loginUserId": "",
				"loginPassword": "",
				"registerUserId": "",
				"registerName": "",
				"registerPassword": "",
				"registerRePassword": ""
			}
		},
		methods: {
			changeBox() {
				this.isLogin = !this.isLogin;
			},
			login() {
				var userId = this.loginUserId;
				var password = this.loginPassword;
				console.log(userId);
				console.log(password);
				uni.request({
					url: this.contextPath + "/login?id=" + userId + "&password=" + password,
					method: "POST",
					header: {
						"content-type": "application/x-www-form-urlencoded"
					},
					success: (res) => {
						console.log(res);
						if (res.data.id == "0") {
							if (res.data.name == "none") {
								uni.showModal({
									content: "用户不存在！",
									showCancel: false
								});
								console.log("用户不存在！");
							}
							else if (res.data.name == "error") {
								uni.showModal({
									content: "密码错误！",
									showCancel: false
								});
								console.log("密码错误！");
							}
							else {
								uni.showModal({
									content: "出现未知错误！！！",
									showCancel: false
								});
								console.log("出现未知错误！！！");
							}
						} else {
							getApp().globalData.user = res.data;
							uni.navigateTo({
								url: "../user/user"
							});
						}
					}
				});
			},
			register() {
				var userId = this.registerUserId;
				var name = this.registerName;
				var password = this.registerPassword;
				var repassword = this.registerRePassword;
				if (password != repassword) {
					console.log("重复密码不正确！");
					uni.showModal({
						content: "重复密码不正确！",
						showCancel: false
					});
				}
				else {
					uni.request({
						url: this.contextPath + "/register?id=" + userId + "&name=" + name + "&password=" + password,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							if (res.data == "exist") {
								uni.showModal({
									content: "用户已存在",
									showCancel: false
								});
								console.log("用户已存在");
							}
							else if (res.data == "success") {
								this.isLogin = true;
							}
							else if (res.data == "wrong format") {
								uni.showModal({
									content: "手机号格式错误",
									showCancel: false
								});
								console.log("手机号格式错误");
							}
							else if (res.data == "wrong name length") {
								uni.showModal({
									content: "请输入用户名",
									showCancel: false
								});
								console.log("请输入用户名");
							}
							else if (res.data == "wrong password length") {
								uni.showModal({
									content: "密码长度应不少于6位数",
									showCancel: false
								});
								console.log("密码长度应不少于6位数");
							}
							else {
								uni.showModal({
									content: "出现未知错误！！！",
									showCancel: false
								});
								console.log("出现未知错误！！！");
							}
						}
					});
				}
			}
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #f7f7f7;
		position: absolute;
		display: flex;
		flex-direction: column;
	}
	.login {
		background-color: #F1F1F1;
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 100rpx 20rpx auto 20rpx;
		padding: 50rpx 20rpx 50rpx 20rpx;
		border-radius: 20rpx;
		box-shadow: 1rpx 1rpx 10rpx #333333;
	}
	.login text {
		font-size: 60rpx;
		font-weight: bold;
		margin-bottom: 30rpx;
	}
	.login input {
		/* background-color: #4CD964; */
		width: 80%;
		margin-bottom: 30rpx;
		font-size: 40rpx;
		padding: 20rpx 20rpx;
		border-radius: 20rpx;
		border: 1rpx solid #333333;
	}
	.login image {
		width: 80rpx;
		height: 80rpx;
		margin-top: 5rpx;
	}
	.input-box {
		display: flex;
		flex-direction: row;
	}
	.login button:nth-child(4) {
		width: 200rpx;
		background-color: #C0C0C0;
		box-shadow: 1rpx 1rpx 10rpx #3F536E;
		margin-bottom: 70rpx;
	}
	.login button:nth-child(5) {
		font-size: 30rpx;
		width: auto;
		height: 70rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		margin-bottom: 10rpx;
	}
	.login button:nth-child(6) {
		font-size: 30rpx;
		width: auto;
		height: 70rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	.register {
		background-color: #F1F1F1;
		display: flex;
		flex-direction: column;
		align-items: center;
		margin: 100rpx 20rpx auto 20rpx;
		padding: 50rpx 20rpx 50rpx 20rpx;
		border-radius: 20rpx;
		box-shadow: 1rpx 1rpx 10rpx #333333;
	}
	.register text {
		font-size: 60rpx;
		font-weight: bold;
		margin-bottom: 30rpx;
	}
	.register input {
		/* background-color: #4CD964; */
		width: 80%;
		margin-bottom: 30rpx;
		font-size: 40rpx;
		padding: 20rpx 20rpx;
		border-radius: 20rpx;
		border: 1rpx solid #333333;
	}
	.register image {
		width: 80rpx;
		height: 80rpx;
		margin-top: 7rpx;
	}
	.register button:nth-child(6) {
		width: 300rpx;
		background-color: #C0C0C0;
		box-shadow: 1rpx 1rpx 10rpx #3F536E;
		margin-bottom: 70rpx;
	}
	.register button:nth-child(7) {
		font-size: 30rpx;
		width: auto;
		height: 70rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		margin-bottom: 10rpx;
	}
</style>
