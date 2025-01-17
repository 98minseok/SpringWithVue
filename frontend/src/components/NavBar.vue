<template>
    <nav>
        <ul>
            <li>
                <RouterLink to ="/">Home</RouterLink>
            </li>
            <li>
                <RouterLink to ="/login">Login</RouterLink>
            </li>
            <li>
                <RouterLink to ="/register">Register</RouterLink>
            </li>
        </ul>
        
        <ul>
            <li>
                <RouterLink to = "/components">Components</RouterLink>
            </li>
            <li>
                <RouterLink to = "/api">API</RouterLink>
            </li>
        </ul>
    </nav>

</template>

<script>

</script>

<style scoped>
    nav{
        padding : 10px;
        height : 50px;
        box-sizing: border-box;
        position: fixed;
        top : 0px;
        left : 0px;
        z-index : 2;
        border-bottom : 1px solid black;
        width : 100%;
        background-color : white;
        display:flex;
        justify-content: space-between;
    }
    ul{
        display : flex;
        gap : 30px;
        list-style: none;
    }
</style>
