<template>
	<view class="page">
		<video class="" src="../../static/video_resource/newer.mp4" controls></video>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.page {
		width: 100%;
		height: 100%;
		background-color: #F1F1F1;
		display: flex;
		flex-direction: column;
		flex-wrap: wrap;
		align-items: center;
		position: absolute;
	}
	.video {
		width: 700rpx;
		height: 1100rpx;
	}
</style>
