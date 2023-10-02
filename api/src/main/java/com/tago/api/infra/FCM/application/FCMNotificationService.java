//package com.tago.api.infra.FCM.application;
//
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.FirebaseMessagingException;
//import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.Notification;
//import com.tago.api.infra.FCM.dto.FCMNotificationRequestDto;
//import com.tago.domain.trip.domain.Trip;
//import com.tago.domain.tripmember.dto.TripMemberDto;
//import com.tago.domain.tripmember.repository.impl.TripMemberCustomRepositoryImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@RequiredArgsConstructor
//@Service
//public class FCMNotificationService {
//
//    private final FirebaseMessaging firebaseMessaging;
//    private final TripMemberCustomRepositoryImpl tripMemberCustomRepository;
//
//    public void sendNotificationToTripMember(Trip trip){
//        List<TripMemberDto> tripMembers = tripMemberCustomRepository.findMembersByTrip(trip);
//
//        for(TripMemberDto tripMemberDto : tripMembers){
//            FCMNotificationRequestDto notificationRequest = FCMNotificationRequestDto.builder()
//                    .body(tripMemberDto.getName() + "님! 여행메이트가 추가되었어요! 클릭해서 확인해보세요.")
//                    .content("클릭해서 확인해보세요.")
//                    .build();
//
//            String firebaseToken = tripMemberDto.getFirebaseToken();
//
//            Message message = Message.builder()
//                    .setNotification(new Message.Notification(
//                            notificationRequest.getBody(),
//                            notificationRequest.getContent()
//                    ))
//                    .setToken(firebaseToken)
//                    .build();
//
//            try {
//                String response = firebaseMessaging.send(message);
//                System.out.println("Successfully sent message: " + response);
//            } catch (FirebaseMessagingException e) {
//                System.err.println("Error sending message: " + e.getLocalizedMessage());
//            }
//
//        }
//    }
//}
