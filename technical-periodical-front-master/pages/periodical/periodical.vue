<template>
	<view>
		<view class="periodical">
			<image :src="prefix + '/' + periodical.periodicalModel.coverPath"></image>
			<text>{{ periodical.periodicalModel.name }}</text>
			<text>作者：{{ periodical.periodicalModel.author }}</text>
			<text>创立日期：{{ periodical.periodicalModel.date }}</text>
			<!-- <text>订阅量：{{ periodical.pubNum }}</text> -->
			<text>{{ periodical.periodicalModel.summary }}</text>
			<button @click="pub()">{{ periodical.isPub ? "取消订阅" : "订阅" }}</button>
		</view>
		<hr>
		<view class="papers">
			<view class="mid">
				<view><text>往期文章</text></view>
				<view><button @click="toAllPapers()">查看全部</button></view>
			</view>
			<view v-for="paper in periodical.periodicalModel.paperShortcuts">
				<view class="paper" @tap="tapPaper(paper.id)">
					<image :src="prefix + '/' + paper.coverPath"></image>
					<view class="paper-main">
						<text>{{ paper.title.substr(0, 13) }}{{ paper.title.length > 13 ? "..." : "" }}</text>
						<br>
						<text>作者：{{ paper.author }}</text>
						<text>{{ paper.date }}</text>
						<hr>
						<text>{{ paper.summary.substr(0, 58) }}{{ paper.summary.length > 58 ? "..." : "" }}</text>
						<br>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"periodical": {},
				"prefix": ""
			}
		},
		methods: {
			pub: function() {
				if (getApp().globalData.user.id == "0") {
					uni.navigateTo({
						url: "../login/login"
					});
				}
				else if (this.periodical.isPub == false) {
					uni.request({
						url: this.contextPath + "/pub?userId=" + getApp().globalData.user.id + "&periodicalId=" + this.periodical.periodicalModel.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							console.log(res);
							this.periodical.isPub = true;
						}
					});
				}
				else {
					uni.request({
						url: this.contextPath + "/dis_pub?userId=" + getApp().globalData.user.id + "&periodicalId=" + this.periodical.periodicalModel.id,
						method: "POST",
						header: {
							"content-type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							console.log(res);
							this.periodical.isPub = false;
						}
					});
				}
			},
			tapPaper: function(id) {
				uni.request({
					url: this.contextPath + "/paper?id=" + id + "&userId=" + getApp().globalData.user.id,
					method: "POST",
					header: {
						"content-type": "application/x-www-form-urlencoded"
					},
					success: (res) => {
						console.log(res);
						getApp().globalData.PaperEncapsulation = res.data;
						uni.navigateTo({
							url: "../paper/paper"
						});
					}
				});
			},
			toAllPapers() {
				uni.request({
					url: this.contextPath + "/papers?message=periodical&" + "id=" + this.periodical.periodicalModel.id,
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
		onShow() {
			this.prefix = this.contextPath;
			this.periodical = getApp().globalData.PeriodicalEncapsulation
		}
	}
</script>

<style>
	.periodical {
		display: flex;
		flex-direction: column;
		align-items: center;
		text-align: center;
		padding: 0 20rpx;
	}
	.periodical image {
		margin-top: 20rpx;
		height: 400rpx;
		width: 280rpx;
		border-radius: 20rpx;
	}
	.periodical text:nth-child(2) {
		font-size: 50rpx;
		font-weight: bold;
		margin: 20rpx 0 10rpx 0;
	}
	.periodical text:nth-child(3) {
		margin-bottom: 10rpx;
	}
	.periodical text:nth-child(4) {
		font-size: 25rpx;
		margin-bottom: 10rpx;
	}
	.periodical text:nth-child(5) {
		font-size: 37rpx;
		font-family: "kaiti";
		font-weight: bold;
		color: #444444;
	}
	.periodical button {
		margin: 10rpx 0;
	}
	
	.mid {
		display: flex;
		flex-direction: row;
		align-items: center;
		width: 100%;
		justify-content: space-between;
	}
	.mid text {
		font-size: 35rpx;
		padding: 0 0 0 10rpx;
	}
	.mid button {
		font-size: 25rpx;
		margin-right: 10rpx;
	}
	
	.paper {
		width: 100%;
		height: 265rpx;
		display: flex;
		flex-direction: row;
		margin-top: 10rpx;
		justify-content: space-between;
		background-color: #F1F1F1;
		border-radius: 10rpx;
	}
	.paper image {
		width: 160rpx;
		height: 228rpx;
		background-color: #999999;
		margin-left: 20rpx;
		margin-top: 20rpx;
		border-radius: 20rpx;
	}
	.paper-main {
		width: 550rpx;
	}
	.paper-main text:nth-child(1) {
		font-weight: bold;
		font-size: 40rpx;
		color: #333333;
	}
	.paper-main text:nth-child(3) {
		font-size: 27rpx;
	}
	.paper-main text:nth-child(4) {
		float: right;
		font-size: 27rpx;
		margin-right: 20rpx;
		font-weight: lighter;
	}
	.paper-main text:nth-child(6) {
		font-size: 37rpx;
		height: 50rpx;
		font-family: "kaiti";
	}
	.paper-main hr {
		background-color: #999999;
		width: 95%;
	}
	
</style>
