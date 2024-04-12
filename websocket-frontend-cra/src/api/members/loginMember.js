import axiosInstance from "../axiosInstance";

export const loginMember = async (loginInfo) => {
    const { data } = await axiosInstance.post("/auth/login", loginInfo);

    return data;    
}