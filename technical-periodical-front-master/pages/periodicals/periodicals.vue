<template>
	<view class="page">
		<view v-for="periodical in periodicals">
			<view class="periodical" @tap="tapPeriodical(periodical.id)">
				<image :src="prefix + '/' + periodical.coverPath"></image>
				<view class="periodical-content">
					<text>{{ periodical.name }}</text>
					<br>
					<text>创立者：{{ periodical.author }}</text>
					<br>
					<text>创立日期：{{ periodical.date }}</text>
					<br>
					<hr>
					<text>简介：</text>
					<text>{{ periodical.summary.substr(0, 69) }}{{ periodical.summary.length > 69 ? "..." : "" }}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				"periodicals": [],
				"prefix": ""
			}
		},
		methods: {
			tapPeriodical(id) {
				uni.request({
					url: this.contextPath + "/periodical?id=" + id + "&userId=" + getApp().globalData.user.id,
					method: "POST",
					header: {
						"content-type": "application/x-www-form-urlencoded"
					},
					success: (res) => {
						console.log(res);
						getApp().globalData.PeriodicalEncapsulation = res.data;
						uni.navigateTo({
							url: "../periodical/periodical"
						});
					}
				});
			}
		},
		onShow() {
			this.periodicals = getApp().globalData.periodicals;
			this.prefix = this.contextPath;
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
	.periodical {
		width: 100%;
		height: 300rpx;
		display: flex;
		flex-direction: row;
		margin-top: 10rpx;
		justify-content: space-between;
		background-color: #F1F1F1;
		border-radius: 10rpx;
	}
	.periodical image {
		width: 160rpx;
		height: 228rpx;
		background-color: #999999;
		margin-left: 20rpx;
		margin-top: 40rpx;
		border-radius: 20rpx;
	}
	.periodical-content {
		width: 560rpx;
	}
	.periodical-content text:nth-child(1) {
		font-size: 40rpx;
		font-weight: bold;
		color: #444455;
	}
	.periodical-content text:nth-child(3) {}
	.periodical-content text:nth-child(5) {
		font-size: 25rpx;
	}
	.periodical-content text:nth-child(8) {
		font-weight: bold;
	}
	.periodical-content text:nth-child(9) {
		font-family: "kaiti";
	}
	.periodical-content hr {
		margin-top: 5rpx;
		background-color: #999999;
		width: 95%;
	}
</style>
