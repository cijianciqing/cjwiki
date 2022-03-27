<template>
    <a-layout id="components-layout-demo-top-side">
        <a-layout-header class="header">
            <GlobalHeader/>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px; margin: 50px">

            <a-divider>With Text</a-divider>
            <a-carousel autoplay>
                <div><h1>No Execuse!!</h1></div>
                <div><h2>Execuse sounds best to the person who's making them up.</h2></div>
                <div><h2>Stop feeling sorry for youself.</h2></div>
                <div><h2>Wake your ass up. It's game on.</h2></div>
            </a-carousel>
            <a-divider>With Text</a-divider>

            <a-button type="primary" @click="play">
                <template #icon><SearchOutlined /></template>
                播放
            </a-button>

<!--            style="margin-left: 20px"-->
            <a-button  type="primary" danger @click="stop">
                <template #icon><SearchOutlined /></template>
                停止
            </a-button>


            <a-button  type="primary"  @click="showLyrics">
                <template #icon><SearchOutlined /></template>
                显示歌词
            </a-button>

        </a-layout-content>
    </a-layout>

    <a-modal
            title="The Lyrics"
            v-model:visible="modalVisible3"
    >

        <div class="11122233223">
            <p>what's stopping you?</p>
            <p></p>
            <p>Are you too tired?</p>
            <p></p>
            <p>Didn't get enough sleep?</p>
            <p></p>
            <p>Don't have enough energy?</p>
            <p></p>
            <p>Don't have enough time?</p>
            <p></p>
            <p>Is that what's stopping you right now?</p>
            <p></p>
            <p>Don't have enough money?</p>
            <p></p>
            <p>Is that the thing?</p>
            <p></p>
            <p>Or is the thing that's stopping you</p>
            <p></p>
            <p>you?</p>
            <p></p>
            <p>Excuses sound best</p>
            <p></p>
            <p>to the person who's making them up.</p>
            <p></p>
            <p>Stop feeling sorry for youself.</p>
            <p></p>
            <p>Get off the pity potty</p>
            <p></p>
            <p>telling everybody you're sad</p>
            <p></p>
            <p>and sob stories,trying to get people</p>
            <p></p>
            <p>to show up to your pity parties</p>
            <p></p>
            <p>and your pity parades.</p>
            <p></p>
            <p>If you ever see me in a rolls royce</p>
            <p></p>
            <p>a 6 or 7 star hotel</p>
            <p></p>
            <p>living my life to the fullest</p>
            <p></p>
            <p>don't get jealous of me.</p>
            <p></p>
            <p>because i worked my ass off to get it.</p>
            <p></p>
            <p>Nobody handed me nothing.</p>
            <p></p>
            <p>Wake your ass up.</p>
            <p></p>
            <p>Awaken the beast inside.</p>
            <p></p>
            <p>It's game on.</p>
            <p></p>
            <p>It's go season.</p>
            <p></p>
            <p>It's time for you to take advantage of the access</p>
            <p></p>
            <p>and the resources that you have</p>
            <p></p>
            <p>in your country and your community.</p>
            <p></p>
            <p>You got a problem with your life?</p>
            <p></p>
            <p>You got a problem with your enviroment?</p>
            <p></p>
            <p>Do something about it.</p>
            <p></p>
            <p>If you want it</p>
            <p></p>
            <p>go get it.</p>
            <p></p>
            <p>Recognise.</p>
            <p></p>
            <p>the excuses are not valid.</p>
            <p></p>
            <p>They're conjured up</p>
            <p></p>
            <p>they're fabricated</p>
            <p></p>
            <p>they're lies.</p>
            <p></p>
            <p>And how do you stop the lies?</p>
            <p></p>
            <p>You stop the lies with the truth.</p>
            <p></p>
            <p>And the truth is</p>
            <p></p>
            <p>you have time</p>
            <p></p>
            <p>you hace the skill</p>
            <p></p>
            <p>you have the knowledge</p>
            <p></p>
            <p>and the support</p>
            <p></p>
            <p>and the willpower</p>
            <p></p>
            <p>and the discipline</p>
            <p></p>
            <p>to get it done.</p>
            <p></p>
            <p>The fruit of everthing good in life</p>
            <p></p>
            <p>begins with a challenge.</p>
            <p></p>
            <p>Everything is uphill that's worthwhile.</p>
            <p></p>
            <p>And it's not going to come to you</p>
            <p></p>
            <p>and it's not going to fall in your lap</p>
            <p></p>
            <p>and it's not going to be something that,oh my gosh</p>
            <p></p>
            <p>it just was so simple</p>
            <p></p>
            <p>it's always going to be difficult.</p>
            <p></p>
            <p>If you want it, you gatta go get it.</p>
            <p></p>
            <p>This is your chance.</p>
            <p></p>
            <p>This is your shot.</p>
            <p></p>
            <p>This is your moment.</p>
            <p></p>
            <p>This is your time.</p>
            <p></p>
            <p>This is your place.</p>
            <p></p>
            <p>This is your opportunity.</p>
            <p></p>
            <p>This is my time</p>
            <p></p>
            <p>This is my moment</p>
            <p></p>
            <p>tomorrow,tomorrow,tomorrow</p>
            <p></p>
            <p>ain't no such thing as tomorrow</p>
            <p></p>
            <p>we only got today.</p>
            <p></p>
            <p>It's your dream.</p>
            <p></p>
            <p>if you're going to have it</p>
            <p></p>
            <p>get your butt up and make it happen</p>
            <p></p>
            <p>if you're going to have it</p>
            <p></p>
            <p>rise and grind.</p>
            <p></p>
            <p>You still got work to do</p>
            <p></p>
            <p>stay on that baskerball court</p>
            <p></p>
            <p>stay on that football field</p>
            <p></p>
            <p>it's grind season homie.</p>

        </div>
    </a-modal>
</template>
<script>
    import {useSound} from '@vueuse/sound'
    import buttonSfx from '@/assets/excuse.mp3'
    import GlobalHeader from "@/components/GlobalHeader.vue";
    import {defineComponent, onMounted, reactive, ref, toRef, watch} from 'vue';
    import {Tool} from "@/util/tool";


    export default defineComponent({
        components: {
            GlobalHeader,
        },



        setup() {

            const modalVisible3 = ref(false);
            const showLyrics = () => {
                modalVisible3.value = true;

            }

            const {play, isPlaying, stop, pause, sound} = useSound(buttonSfx, {
                interrupt: true,//不重复播放
                soundEnabled: true,//允许有声音？？？？
            })



            return {
                play,
                stop,

                modalVisible3,
                showLyrics,

            }
        },
    });
</script>

<style scoped>
    /* For demo */
    .ant-carousel >>> .slick-slide {
        text-align: center;
        height: 200px;
        line-height: 200px;
        background: #364d79;
        overflow: hidden;
    }

    .ant-carousel >>> .slick-slide h2 {
        color: #fff;
    }

    .ant-carousel >>> .slick-slide h1 {
        color: #fff;
    }
</style>