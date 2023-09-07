package com.tago.api.slack.application;


import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.composition.TextObject;
import com.tago.domain.issue.dto.IssueDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.ArrayList;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Service
@RequiredArgsConstructor
public class SlackService{

    @Value(value = "${slack.bot-token}")
    private String token;

    @Value(value="${slack.channel.monitor}")
    private String channel;

    public void sendMessageToSlack(IssueDto issueDto){
        List<TextObject> textObjects = new ArrayList<>();
        textObjects.add(markdownText("*문의 제목:*\n" + issueDto.getType()));
        textObjects.add(markdownText("*문의 내용:*\n" + issueDto.getDetail()));

        MethodsClient methods = Slack.getInstance().methods(token);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .blocks(asBlocks(
                        //추후에 수정 예정
                        //header(header -> header.text(plainText(issueDto.getType() + "님이 문의를 남겨주셨습니다!"))),
                        divider(),
                        section(section -> section.fields(textObjects)
                        ))).build();

        try {
            methods.chatPostMessage(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message to Slack", e);
        }
    }
}