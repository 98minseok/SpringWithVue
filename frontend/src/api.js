import axios from "axios"
import useCookies from "./moduels/cookies";

const {getCookie} = useCookies();
const BASED_URL = 'http://localhost:8080'
const createURL = (url) => BASED_URL + url;
export const getMembers = async() => {

    const data = await axios.get(createURL("/members"))
    .then((res) => res.data)
    .catch((err) => console.log(err));

    console.log(data);

    return data;
}  

export const postMember = async(member) => {
    const data = await axios.post(createURL("/members"),member)
    .then((res) => res.data)
    .catch((err) => console.log(err));

    console.log(data);

    return data;
}

export const deleteMember = async(id) => {
    const data = await axios.delete(createURL(`/members?id=${id}`,{
        headers: {
            'Content-Type': 'application/json', // 명시적 설정
        }
    })).then((res) => res.data)
    .catch((err) => console.log(err));

    return data;
}

export const getMemberById = async(id) => {
    const data = await axios.get(createURL(`/members/id/${id}`,{
        headers: {
            'Content-Type': 'application/json', // 명시적 설정
        }
    })).then((res) => res.data)
    .catch((err) => console.log(err));

    return data;
}

export const updateMember = async(member) =>{
    console.log(member);
    const data = await axios.put(createURL(`/members`),member).then((res) => res.data)
    .catch((err) => console.log(err));

    return data;
}

export const loginMember = async(member) => {

    const data = await axios.post(createURL(`/members/login`),member).then((res) => res.data).
    catch((err) => console.log(err));

    return data;
}

export const testToken = async() => {
    const cookie = getCookie("token");
    console.log(cookie)
    const data = await axios.get(createURL(`/api/secure/test`),{
        headers : {
            Authorization : `Bearer ${cookie}`
        }
    }).then((res) => res.data).
    catch((err) => console.log(err));

    return data;
}