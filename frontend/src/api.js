import axios from "axios"
import useCookies from "./moduels/cookies";
import apiClient from "./moduels/axios";

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
    const data = await apiClient(createURL(`/api/secure/test`),{
        headers : {
            "Authorization" : `Bearer ${cookie}`,
            "Content-Type" : 'text/html'
        }
    }).then((res) => res.data)
    .catch((err) => {
        console.log(err)
    });

    return data;
}

export const saveBoard = async(board) => {
  const cookie = getCookie("token");
  const data = await apiClient.post(createURL('/api/secure/board'),board,{
    headers : {
      "Authorization" : `Bearer ${cookie}`
  }
  }).then((res) => res.data)
    .catch((err) => {
        console.log(err)
    })

    return data;
  }

export const getBoards = async() => {
  try{
    const data = await axios.get(createURL('/board'))
    return data;
  }catch(err){
    console.log(err);
  }
}

export const getBoardById = async(id) => {
  try{
    const data = await axios.get(createURL(`/board/${id}`))
    return data;
  }catch(err){
    console.log(err);
  }
}

export const hitHeart = async(id) => {
  try{
    const data = await axios.post(createURL(`/board/${id}`),{})
    return data;
  }catch(err){
    console.log(err);
  }
}


export const editBoard = async(board) => {
  const cookie = getCookie("token");
  const data = await apiClient.put(createURL('/api/secure/board'),board,{
    headers : {
      "Authorization" : `Bearer ${cookie}`
  }
  }).then((res) => res.data)
    .catch((err) => {
        console.log(err)
    })

    return data;
  }

  export const deleteBoard = async(id) => {
    try{
      const data = await axios.delete(createURL(`/board/${id}`),{})
      return data;
    }catch(err){
      console.log(err);
    }
  }


  export const getReply = async(parentId) => {

    const data = await apiClient.get(createURL(`/reply/${parentId}`))
    .then((res) => res.data)
      .catch((err) => {
          console.log(err)
      })

      return data;
  }


  export const saveReply = async(reply) => {
    const cookie = getCookie("token");
    const data = await apiClient.post(createURL('/api/secure/reply'),reply,{
      headers : {
        "Authorization" : `Bearer ${cookie}`
    }
    }).then((res) => res.data)
      .catch((err) => {
          console.log(err)
      })

      return data;
    }

  export const editReply = async(reply) => {
    const cookie = getCookie("token");
    const data = await apiClient.put(createURL('/api/secure/reply'),reply,{
      headers : {
        "Authorization" : `Bearer ${cookie}`
    }
    }).then((res) => res.data)
      .catch((err) => {
          console.log(err)
      })

      return data;
    }

  export const deleteReply = async(id) => {
    try{
      const data = await axios.delete(createURL(`/reply/${id}`),{})
      return data.data;
    }catch(err){
      console.log(err);
    }
  }
