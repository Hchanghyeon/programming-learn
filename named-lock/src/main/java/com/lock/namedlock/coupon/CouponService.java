package com.lock.namedlock.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Transactional
    public void getCoupon(Long couponId) {
        Coupon coupon = couponRepository.findByIdWithLock(couponId);

        if (coupon.getAmount() <= 0) {
            throw new RuntimeException("쿠폰 잔여량이 0입니다.");
        }

        coupon.updateAmount(coupon.getAmount() - 1);
        couponRepository.save(coupon); // 변경 사항 저장
    }
}
