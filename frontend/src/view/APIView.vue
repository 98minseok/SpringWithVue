<template>
    <div class ="main">
        <section class ="left-nav">
            <ul>
                <li :key = "api" v-for="api in apis" class ="api-link" :class ="view_api == api ? 'active' : ''">
                    <a href="#" @click ="onClickApi(api)">{{ api }}</a>
                </li>
            </ul>
        </section>
        <section class ="api-container">
            <section class ="api-view-container">
                {{ view_api == "" ? "컴포넌트를 선택해주세요" : ""}}
                <component :is='view_api'/>
            </section>
        </section>
    </div>
</template>

<script>
import MemberRegister from '@/components/MemberRegister.vue';
import MemberList from '@/components/MemberList.vue';
import MemberUpdate from '@/components/MemberUpdate.vue';
import MemberLogin from '@/components/MemberLogin.vue';
import BoardForm from '@/components/BoardForm.vue'
import BoardList from '@/components/BoardList.vue';
import { provide, ref } from 'vue';

    export default{
        setup(){
            const apis = ["MemberRegister","MemberList","MemberUpdate","MemberLogin","BoardForm","BoardList"]
            const update_id = ref(-1);
            provide('update_id',update_id)

            const view_api = ref("")
            provide('view_api',view_api)
            const onClickApi = (api) => {
                view_api.value = api;
            }

            return{
                view_api,onClickApi,apis,
            }
        },
        components: {
            MemberRegister,MemberList,MemberUpdate,MemberLogin,BoardForm,BoardList
        }
    }
</script>

<style lang="css" scoped>
    .main{
        display:flex;
        position : absolute;
        left:0;
        top:0px;
        padding-top : 50px;
        box-sizing: border-box;
        height : 100vh;
        width  : 100vw;
    }
    .left-nav{
        flex : 1;
        padding : 10px;
        box-sizing: border-box;
        border-right : 1px solid black;
    }
    .api-container{
        width : 100%;
        flex: 9;
        display:flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        box-sizing: border-box;
    }
    .api-link{
        border-radius : 10px;
        padding-left : 5px;
    }
    .active{
        font-weight : bold;
        background-color: aquamarine;
    }

    .api-props-container{
        width: 100%;
        display:flex;
        justify-content: center;
        align-items: center;
        background-color: rgb(247, 248, 248);
        flex : 3
    }

    .api-view-container{
        flex : 7;
        display:flex;
        justify-content: center;
        align-items: center;
        border-bottom : 1px solid black;
        width:100%;
        padding : 10px;
        box-sizing: border-box;
    }
</style>
