import React, { useState, useEffect } from 'react';
import { Header } from '../../components/Header/Header';
import FloatingMenu from '../../components/FloatingMenu/FloatingMenu';
import { IndexSection } from './components/IndexSection';

export const IndexPage = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            setIsLoggedIn(true); 
        } else {
            setIsLoggedIn(false); 
        }
    }, []); 

    return <>
        <Header />
        <IndexSection/>
        {isLoggedIn && <FloatingMenu />}
    </>;
}
