<template>
    <v-btn @click ="onClickFunction">
        <slot>{{default_slot}}</slot>
    </v-btn>
</template>

<script>
    export default{
        props : {
            onClickFunction : {
                type : Function,
                default : (evt) => {evt.preventDefault() ; alert("버튼이 클릭됨")}
            },
        },
        setup() {
            const default_slot = "버튼";
            return {default_slot}
        }
    }
</script>

<style lang="css" scoped>
    .custom-button{
        padding : 10px 20px;
        border-radius: 10px;
        border : 1px solid black;
        cursor: pointer;
    }
    .custom-button:hover{
        background-color: antiquewhite;
    }
</style>