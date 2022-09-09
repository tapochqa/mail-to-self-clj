(ns mail-to-self-clj.messages)

(def test-messages [{ :type "straight"
                      :content {:message_id 5
                                :from { :id 163440129 
                                        :is_bot false
                                        :first_name "максим" 
                                        :last_name "сакович" 
                                        :username "briskovic" 
                                        :language_code "en" 
                                        :is_premium true} 
                                :chat { :id 163440129 
                                        :first_name "макс" 
                                        :username "briskovic" 
                                        :type "private"} 
                                :date 1662588699 
                                :text "ekfj"}}
                    
                    { :type "channel-anon"
                      :content {  :date 1662597821 
                                  :forward_from_chat {:id -1001315078272 
                                                      :title "Артемий Лебедев" 
                                                      :username "temablog" 
                                                      :type "channel"} 
                                  :entities [{:offset 67
                                              :length 28 
                                              :type "url"}] 
                                  :chat { :id 163440129
                                          :first_name "макс" 
                                          :username "briskovic" 
                                          :type "private"} 
                                  :message_id 91 
                                  :from { :id 163440129
                                          :is_bot false 
                                          :first_name "макс" 
                                          :username "briskovic" 
                                          :language_code "en" 
                                          :is_premium true}
                                  :forward_from_message_id 5829 
                                  :forward_date 1662593205 
                                  :text "Заиц, это было непросто, но я сдвинул для тебя горы и залил видос: https://youtu.be/zQ3ajQHJvYI"}}
                    
                    { :type "dm-open"
                      :content {  :message_id 97
                                  :from {:id 163440129 
                                         :is_bot false 
                                         :first_name "макс" 
                                         :username "briskovic" 
                                         :language_code "en" 
                                         :is_premium true} 
                                  :chat {:id 163440129 
                                         :first_name "макс" 
                                         :username "briskovic" 
                                         :type "private"} 
                                  :date 1662599681 
                                  :forward_from {:id 334211489 
                                                 :is_bot false 
                                                 :first_name "NABIRIY" 
                                                 :username "NABIRIY"} 
                                  :forward_date 1662538575 
                                  :text "просто задачка попалась интересная"}}

                    { :type "dm-close"
                      :content {  :message_id 103
                                  :from {:id 163440129
                                          :is_bot false
                                          :first_name "макс"
                                          :username "briskovic"
                                          :language_code "en"
                                          :is_premium true}
                                  :chat{ :id 163440129
                                          :first_name "макс"
                                          :username "briskovic"
                                          :type "private"}
                                  :date 1662605643
                                  :forward_sender_name "Qq"
                                  :forward_date 1662571512
                                  :text "Привет, подскажите пожалуйста, книгу по clojure. Я знаю груви и java. Интересно потыкать кложуру"}}

                    { :type "channel-signed"
                      :content {  :video { :thumb {:file_id "AAMCAgADGQEAA3FjGVsSeftzFaPY4H74Tmcom9zJOgAC-hQAAtCpgUkhkVe4aZo7pgEAB20AAykE"
                                                   :file_unique_id "AQAD-hQAAtCpgUly"
                                                   :file_size 13571
                                                   :width 180
                                                   :height 320}
                                            :file_name "Не верь словам.mp4"
                                            :mime_type "video/mp4"
                                            :width 1080
                                            :duration 127
                                            :file_size 257429577
                                            :file_unique_id "AgAD-hQAAtCpgUk"
                                            :file_id "BAACAgIAAxkBAAPOYxqvwb5JbmFS8sy6ndbYPG9dDj4AAvoUAALQqYFJIZFXuGmaO6YpBA"
                                            :height 1920}
                                    :caption "Новую версию на русском записывали еще в 2020м. Спешка, срочно, надо было запилить трек для танца. Спасибо Сергею Довгий за помощь и запись в тот сложный момент. На онлайн концерте пел ее, но думаю все же надо выпустить ее в нормальном варианте, сейчас она очень актуальна для меня. Трек на английском называется - I'll Stay, а на Русском будет - Ты сможешь."
                                    :date 1662606098
                                    :forward_from_chat {:id -1001713050300,
                                                        :title "Царёв на связи"
                                                        :username "TsarevSuperHi"
                                                        :type "channel"}
                                   :chat {:id 163440129,
                                          :first_name "макс"
                                          :username "briskovic"
                                          :type "private"}
                                   :message_id 113
                                   :from {:id 163440129
                                          :is_bot false
                                          :first_name "макс"
                                          :username "briskovic"
                                          :language_code "en"
                                          :is_premium true}
                                   :forward_signature "MolotFX"
                                   :forward_from_message_id 12
                                   :forward_date 1647329447}}

                      { :type "photo"
                        :content { :date 1662679236,
                                   :forward_from_chat
                                   {:id -1001758883409,
                                    :title "girls cum first",
                                    :username "girlscumfirsst",
                                    :type "channel"},
                                   :chat
                                   {:id 163440129,
                                    :first_name "макс",
                                    :username "briskovic",
                                    :type "private"},
                                   :message_id 154,
                                   :photo
                                   [{:file_id
                                     "AgACAgIAAxkBAAOaYxp4xK7AgYgUvmeW1ScH1sgqxGoAAgy9MRsomslIje1Yxj5SAYgBAAMCAANzAAMpBA",
                                     :file_unique_id "AQADDL0xGyiayUh4",
                                     :file_size 1410,
                                     :width 51,
                                     :height 90}
                                    {:file_id
                                     "AgACAgIAAxkBAAOaYxp4xK7AgYgUvmeW1ScH1sgqxGoAAgy9MRsomslIje1Yxj5SAYgBAAMCAANtAAMpBA",
                                     :file_unique_id "AQADDL0xGyiayUhy",
                                     :file_size 16665,
                                     :width 180,
                                     :height 320}
                                    {:file_id
                                     "AgACAgIAAxkBAAOaYxp4xK7AgYgUvmeW1ScH1sgqxGoAAgy9MRsomslIje1Yxj5SAYgBAAMCAAN4AAMpBA",
                                     :file_unique_id "AQADDL0xGyiayUh9",
                                     :file_size 69500,
                                     :width 450,
                                     :height 800}
                                    {:file_id
                                     "AgACAgIAAxkBAAOaYxp4xK7AgYgUvmeW1ScH1sgqxGoAAgy9MRsomslIje1Yxj5SAYgBAAMCAAN5AAMpBA",
                                     :file_unique_id "AQADDL0xGyiayUh-",
                                     :file_size 95721,
                                     :width 720,
                                     :height 1280}],
                                   :from
                                   {:id 163440129,
                                    :is_bot false,
                                    :first_name "макс",
                                    :username "briskovic",
                                    :language_code "en",
                                    :is_premium true},
                                   :media_group_id "13301433893742442",
                                   :forward_from_message_id 3756,
                                   :forward_date 1662553116}}])


