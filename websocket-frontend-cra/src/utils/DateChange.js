export const formatDate = (date) => {
    let year = date.getFullYear(); // 년도
    let month = date.getMonth() + 1; // 월 (0부터 시작하므로 +1을 해줍니다)
    let day = date.getDate(); // 일
    let hour = date.getHours(); // 시간
    let minute = date.getMinutes(); // 분
  
    // 한 자리 숫자일 경우 앞에 0을 붙여줍니다 (예: 9 -> 09)
    month = month < 10 ? '0' + month : month;
    day = day < 10 ? '0' + day : day;
    hour = hour < 10 ? '0' + hour : hour;
    minute = minute < 10 ? '0' + minute : minute;
  
    return `${year}년 ${month}월 ${day}일 ${hour}시 ${minute}분`;
  }

  export const formatDateYearMonthDay = (date) => {
    let year = date.getFullYear(); // 년도
    let month = date.getMonth() + 1; // 월 (0부터 시작하므로 +1을 해줍니다)
    let day = date.getDate(); // 일
  
    // 한 자리 숫자일 경우 앞에 0을 붙여줍니다 (예: 9 -> 09)
    month = month < 10 ? '0' + month : month;
    day = day < 10 ? '0' + day : day;
  
    return `${year}년 ${month}월 ${day}일`;
  }