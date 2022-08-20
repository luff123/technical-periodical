<template>
	<view class="page">
		<view v-for="paper in papers">
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
</template>

<script>
	export default {
		data() {
			return {
				"papers": [],
				"prefix": ""
			}
		},
		methods: {
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
			}
		},
		onShow() {
			this.prefix = this.contextPath;
			this.papers = getApp().globalData.papers;
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
	.paper {
		width: 100%;
		height: 265rpx;
		display: flex;
		flex-direction: row;
		margin-top: 10rpx;
		justify-content: space-between;
		background-color: #f7f7f7;
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
