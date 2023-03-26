package dev.kepler88d

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.KeyboardReplyMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import com.github.kotlintelegrambot.entities.keyboard.KeyboardButton

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val bot = bot {
                token = System.getenv("TOKEN")
                dispatch {

                    command("start") {
                        bot.sendMessage(
                            ChatId.fromId(message.chat.id),
                            text = """
                    Tasks Bot – бот для управления задачами прямо из Telegram
                        
                    Начните пользоваться им уже сейчас, используя одну из команд в выпадающем меню внизу
                    """.trimIndent(),
                            replyMarkup = KeyboardReplyMarkup(keyboard = generateUsersButton(), resizeKeyboard = true)
                        )
                    }

                    text("Создать задачу") {
                        bot.sendMessage(
                            chatId = ChatId.fromId(message.chat.id),
                            text = "Введите название задачи"
                        )
                    }

                    text("Списки") {
                        bot.sendMessage(
                            chatId = ChatId.fromId(message.chat.id),
                            text = "Списки ваших задач",
                            replyMarkup = InlineKeyboardMarkup.create(
                                listOf(
                                    InlineKeyboardButton.CallbackData(
                                        text = "Задачи на сегодня", callbackData = "Задачи на сегодня"
                                    )
                                ),
                                listOf(
                                    InlineKeyboardButton.CallbackData(text = "Важное", callbackData = "showAlert")
                                ),
                                listOf(
                                    InlineKeyboardButton.CallbackData(text = "Личное", callbackData = "showAlert")
                                ),
                                listOf(
                                    InlineKeyboardButton.CallbackData(text = "Работа", callbackData = "showAlert")
                                ),
                            )
                        )
                    }

                    callbackQuery("Задачи на сегодня") {
                        bot.sendMessage(
                            chatId = ChatId.fromId(callbackQuery.message?.chat?.id ?: return@callbackQuery),
                            text = """Список "Задачи на сегодня"""",
                            replyMarkup = InlineKeyboardMarkup.create(
                                listOf(
                                    InlineKeyboardButton.CallbackData(
                                        text = "Сходить за продуктами",
                                        callbackData = "testButton"
                                    )
                                ),
                                listOf(
                                    InlineKeyboardButton.CallbackData(
                                        text = "Дочитать книгу",
                                        callbackData = "showAlert"
                                    )
                                ),
                                listOf(
                                    InlineKeyboardButton.CallbackData(
                                        text = "Приготовить ужин",
                                        callbackData = "showAlert"
                                    )
                                ),
                                listOf(
                                    InlineKeyboardButton.CallbackData(text = "< Назад", callbackData = "start")
                                ),
                            )
                        )
                    }

                    callbackQuery("start") {
                        bot.sendMessage(
                            chatId = ChatId.fromId(callbackQuery.message?.chat?.id ?: return@callbackQuery),
                            text = """
                    Tasks Bot – бот для управления задачами прямо из Telegram
                        
                    Начните пользоваться им уже сейчас, используя одну из команд в выпадающем меню внизу
                    """.trimIndent(),
                            replyMarkup = KeyboardReplyMarkup(keyboard = generateUsersButton(), resizeKeyboard = true)
                        )
                    }

                    text("О проекте") {
                        bot.sendMessage(
                            chatId = ChatId.fromId(message.chat.id),
                            text = """
                    Бот разрабатывается и поддерживается @kepler88d. 
                    Можете подписаться на мой канал о технологиях @LogDotB
                    """.trimIndent()
                        )
                    }
                }
            }

            bot.startPolling()
        }

        private fun generateUsersButton(): List<List<KeyboardButton>> {
            return listOf(
                listOf(KeyboardButton("Создать задачу")),
                listOf(KeyboardButton("Списки задач")),
                listOf(KeyboardButton("Удалить задачу")),
                listOf(KeyboardButton("О проекте")),
            )
        }

    }
}
