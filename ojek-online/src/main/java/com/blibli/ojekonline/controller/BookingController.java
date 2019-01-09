package com.blibli.ojekonline.controller;

import com.blibli.ojekonline.model.Booking;
import com.blibli.ojekonline.model.Driver;
import com.blibli.ojekonline.model.Member;
import com.blibli.ojekonline.service.BookingService;
import com.blibli.ojekonline.service.DriverService;
import com.blibli.ojekonline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/api/booking/all")
    public BaseResponse<List<Booking>> getAllBooking() {
        BaseResponse response = new BaseResponse();
        response.setValue(bookingService.getBookingList());
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }

    @GetMapping("/api/booking/all/member/{id}")
    public BaseResponse<List<Booking>> getMemberBooking(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        response.setValue(bookingService.findBookingByMemberId(id));
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }

    @GetMapping("/api/booking/all/driver/{id}")
    public BaseResponse<List<Booking>> getDriverBooking(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        response.setValue(bookingService.findBookingByDriverId(id));
        response.setStatus(HttpStatus.OK.toString());
        return response;
    }

    @GetMapping("/api/booking/{id}")
    public BaseResponse<Booking> getBooking(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        try {
            response.setValue(bookingService.getBookingById(id));
            response.setStatus(HttpStatus.OK.toString());
        } catch (RuntimeException e) {
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @PostMapping("api/booking")
    public BaseResponse<String> saveBooking(@RequestBody BookingRequest bookingRequest){
        BaseResponse response = new BaseResponse();
        Booking booking = new Booking();
        booking.setBalanceTaken(bookingRequest.getBalanceTaken());
        booking.setCanceled(false);
        booking.setFinished(false);
        booking.setBookedDate(bookingRequest.getBookedDate());
        booking.setScheduledDate(bookingRequest.getScheduledDate());
        Driver driver = driverService.getDriverById(bookingRequest.getDriverId());
        booking.setDriver(driver);
        Member member = memberService.getMemberById(bookingRequest.getMemberId());
        booking.setMember(member);
        try{
            bookingService.saveBooking(booking);
            response.setValue("Save success!");
            response.setStatus(HttpStatus.OK.toString());
        }catch(RuntimeException e){
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @GetMapping("/api/booking/{id}/finished")
    public BaseResponse<String> changeBookingToFinished(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        try{
            Booking booking = bookingService.getBookingById(id);
            bookingService.finishedBooking(booking);
            response.setValue("Booking : " + id + " is finished!");
            response.setStatus(HttpStatus.OK.toString());
        }catch(RuntimeException e){
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

    @GetMapping("/api/booking/{id}/canceled")
    public BaseResponse<String> changeBookingToCanceled(@PathVariable int id){
        BaseResponse response = new BaseResponse();
        try{
            Booking booking = bookingService.getBookingById(id);
            bookingService.cancelBooking(booking);
            response.setValue("Booking : " + id + " is canceled!");
            response.setStatus(HttpStatus.OK.toString());
        }catch(RuntimeException e){
            response.setValue(null);
            response.setStatus(HttpStatus.NOT_FOUND.toString());
        }
        return response;
    }

}
