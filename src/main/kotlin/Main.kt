import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main(args: Array<String>) {
    val bot = bot {
        token = System.getenv("TOKEN")
        dispatch {
            text {
                bot.sendMessage(ChatId.fromId(message.chat.id), text = text)
            }
        }
    }

    bot.startPolling()
}
