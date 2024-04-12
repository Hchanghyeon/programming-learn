import axiosInstance from "../axiosInstance";

export const registerMemger = async (member) => {
    const { data } = await axiosInstance.post("/members", member);

    return data;    
}