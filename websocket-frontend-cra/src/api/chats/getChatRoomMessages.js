import axiosInstance from "../axiosInstance";

export const getChatRoomMessages = async (chatRoomId) => {
    const { data } = await axiosInstance.get(`/chat/rooms/${chatRoomId}`);

    return data;    
}