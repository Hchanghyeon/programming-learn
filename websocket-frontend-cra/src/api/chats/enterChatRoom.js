import axiosInstance from "../axiosInstance";

export const enterChatRoom = async (chatRoomId) => {
    const { data } = await axiosInstance.post(`/chat/rooms/enter/${chatRoomId}`, "", {
        headers: {
            Authorization: "Bearer " + localStorage.getItem('accessToken')
        }
    });
    return data;    
}