package com.bucket.manus.rest;

import com.bucket.manus.agent.BucketManus;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * <p>
 * ai rest接口
 * </p>
 *
 * @author cyq
 * @date 2025/9/19 21:56
 */
@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private ToolCallback[] allTools;
    @Resource
    private ChatModel dashscopeChatModel;

    /**
     * 流式调用 Manus 超级智能体
     *
     * @param message
     * @return
     */
    @GetMapping("/manus/chat")
    public SseEmitter doChatWithManus(String message) {
        BucketManus bucketManus = new BucketManus(allTools, dashscopeChatModel);
        return bucketManus.runStream(message);
    }
}
