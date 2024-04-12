import axiosInstance from "../axiosInstance";

export const getChatRooms = async () => {
    const { data } = await axiosInstance.get("/chat/rooms");

    return data;    
}