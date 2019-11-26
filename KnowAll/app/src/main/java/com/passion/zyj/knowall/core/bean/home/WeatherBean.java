package com.passion.zyj.knowall.core.bean.home;

public class WeatherBean {

    /**
     * sk : {"temp":"4","wind_direction":"东风","wind_strength":"4级","humidity":"96%","time":"11:23"}
     * today : {"temperature":"5℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东风微风","week":"星期四","city":"苏州","date_y":"2019年02月14日","dressing_index":"冷","dressing_advice":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
     * future : {"day_20190214":{"temperature":"5℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东风微风","week":"星期四","date":"20190214"},"day_20190215":{"temperature":"3℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"北风微风","week":"星期五","date":"20190215"},"day_20190216":{"temperature":"1℃~5℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风微风","week":"星期六","date":"20190216"},"day_20190217":{"temperature":"2℃~6℃","weather":"雨夹雪转阴","weather_id":{"fa":"06","fb":"02"},"wind":"东北风微风","week":"星期日","date":"20190217"},"day_20190218":{"temperature":"6℃~10℃","weather":"阴","weather_id":{"fa":"02","fb":"02"},"wind":"东南风微风","week":"星期一","date":"20190218"},"day_20190219":{"temperature":"3℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"北风微风","week":"星期二","date":"20190219"},"day_20190220":{"temperature":"2℃~6℃","weather":"雨夹雪转阴","weather_id":{"fa":"06","fb":"02"},"wind":"东北风微风","week":"星期三","date":"20190220"}}
     */

    private SkBean sk;
    private TodayBean today;
    private FutureBean future;

    public SkBean getSk() {
        return sk;
    }

    public void setSk(SkBean sk) {
        this.sk = sk;
    }

    public TodayBean getToday() {
        return today;
    }

    public void setToday(TodayBean today) {
        this.today = today;
    }

    public FutureBean getFuture() {
        return future;
    }

    public void setFuture(FutureBean future) {
        this.future = future;
    }

    public static class SkBean {
        /**
         * temp : 4
         * wind_direction : 东风
         * wind_strength : 4级
         * humidity : 96%
         * time : 11:23
         */

        private String temp;
        private String wind_direction;
        private String wind_strength;
        private String humidity;
        private String time;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_strength() {
            return wind_strength;
        }

        public void setWind_strength(String wind_strength) {
            this.wind_strength = wind_strength;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class TodayBean {
        /**
         * temperature : 5℃~7℃
         * weather : 小雨
         * weather_id : {"fa":"07","fb":"07"}
         * wind : 东风微风
         * week : 星期四
         * city : 苏州
         * date_y : 2019年02月14日
         * dressing_index : 冷
         * dressing_advice : 天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。
         * uv_index : 最弱
         * comfort_index :
         * wash_index : 不宜
         * travel_index : 较不宜
         * exercise_index : 较不宜
         * drying_index :
         */

        private String temperature;
        private String weather;
        private WeatherIdBean weather_id;
        private String wind;
        private String week;
        private String city;
        private String date_y;
        private String dressing_index;
        private String dressing_advice;
        private String uv_index;
        private String comfort_index;
        private String wash_index;
        private String travel_index;
        private String exercise_index;
        private String drying_index;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public WeatherIdBean getWeather_id() {
            return weather_id;
        }

        public void setWeather_id(WeatherIdBean weather_id) {
            this.weather_id = weather_id;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate_y() {
            return date_y;
        }

        public void setDate_y(String date_y) {
            this.date_y = date_y;
        }

        public String getDressing_index() {
            return dressing_index;
        }

        public void setDressing_index(String dressing_index) {
            this.dressing_index = dressing_index;
        }

        public String getDressing_advice() {
            return dressing_advice;
        }

        public void setDressing_advice(String dressing_advice) {
            this.dressing_advice = dressing_advice;
        }

        public String getUv_index() {
            return uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
        }

        public String getComfort_index() {
            return comfort_index;
        }

        public void setComfort_index(String comfort_index) {
            this.comfort_index = comfort_index;
        }

        public String getWash_index() {
            return wash_index;
        }

        public void setWash_index(String wash_index) {
            this.wash_index = wash_index;
        }

        public String getTravel_index() {
            return travel_index;
        }

        public void setTravel_index(String travel_index) {
            this.travel_index = travel_index;
        }

        public String getExercise_index() {
            return exercise_index;
        }

        public void setExercise_index(String exercise_index) {
            this.exercise_index = exercise_index;
        }

        public String getDrying_index() {
            return drying_index;
        }

        public void setDrying_index(String drying_index) {
            this.drying_index = drying_index;
        }

        public static class WeatherIdBean {
            /**
             * fa : 07
             * fb : 07
             */

            private String fa;
            private String fb;

            public String getFa() {
                return fa;
            }

            public void setFa(String fa) {
                this.fa = fa;
            }

            public String getFb() {
                return fb;
            }

            public void setFb(String fb) {
                this.fb = fb;
            }
        }
    }

    public static class FutureBean {
        /**
         * day_20190214 : {"temperature":"5℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东风微风","week":"星期四","date":"20190214"}
         * day_20190215 : {"temperature":"3℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"北风微风","week":"星期五","date":"20190215"}
         * day_20190216 : {"temperature":"1℃~5℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风微风","week":"星期六","date":"20190216"}
         * day_20190217 : {"temperature":"2℃~6℃","weather":"雨夹雪转阴","weather_id":{"fa":"06","fb":"02"},"wind":"东北风微风","week":"星期日","date":"20190217"}
         * day_20190218 : {"temperature":"6℃~10℃","weather":"阴","weather_id":{"fa":"02","fb":"02"},"wind":"东南风微风","week":"星期一","date":"20190218"}
         * day_20190219 : {"temperature":"3℃~7℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"北风微风","week":"星期二","date":"20190219"}
         * day_20190220 : {"temperature":"2℃~6℃","weather":"雨夹雪转阴","weather_id":{"fa":"06","fb":"02"},"wind":"东北风微风","week":"星期三","date":"20190220"}
         */

        private Day20190214Bean day_20190214;
        private Day20190215Bean day_20190215;
        private Day20190216Bean day_20190216;
        private Day20190217Bean day_20190217;
        private Day20190218Bean day_20190218;
        private Day20190219Bean day_20190219;
        private Day20190220Bean day_20190220;

        public Day20190214Bean getDay_20190214() {
            return day_20190214;
        }

        public void setDay_20190214(Day20190214Bean day_20190214) {
            this.day_20190214 = day_20190214;
        }

        public Day20190215Bean getDay_20190215() {
            return day_20190215;
        }

        public void setDay_20190215(Day20190215Bean day_20190215) {
            this.day_20190215 = day_20190215;
        }

        public Day20190216Bean getDay_20190216() {
            return day_20190216;
        }

        public void setDay_20190216(Day20190216Bean day_20190216) {
            this.day_20190216 = day_20190216;
        }

        public Day20190217Bean getDay_20190217() {
            return day_20190217;
        }

        public void setDay_20190217(Day20190217Bean day_20190217) {
            this.day_20190217 = day_20190217;
        }

        public Day20190218Bean getDay_20190218() {
            return day_20190218;
        }

        public void setDay_20190218(Day20190218Bean day_20190218) {
            this.day_20190218 = day_20190218;
        }

        public Day20190219Bean getDay_20190219() {
            return day_20190219;
        }

        public void setDay_20190219(Day20190219Bean day_20190219) {
            this.day_20190219 = day_20190219;
        }

        public Day20190220Bean getDay_20190220() {
            return day_20190220;
        }

        public void setDay_20190220(Day20190220Bean day_20190220) {
            this.day_20190220 = day_20190220;
        }

        public static class Day20190214Bean {
            /**
             * temperature : 5℃~7℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 东风微风
             * week : 星期四
             * date : 20190214
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanX {
                /**
                 * fa : 07
                 * fb : 07
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20190215Bean {
            /**
             * temperature : 3℃~7℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 北风微风
             * week : 星期五
             * date : 20190215
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXX {
                /**
                 * fa : 07
                 * fb : 07
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20190216Bean {
            /**
             * temperature : 1℃~5℃
             * weather : 小雨转多云
             * weather_id : {"fa":"07","fb":"01"}
             * wind : 北风微风
             * week : 星期六
             * date : 20190216
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXX {
                /**
                 * fa : 07
                 * fb : 01
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20190217Bean {
            /**
             * temperature : 2℃~6℃
             * weather : 雨夹雪转阴
             * weather_id : {"fa":"06","fb":"02"}
             * wind : 东北风微风
             * week : 星期日
             * date : 20190217
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXX {
                /**
                 * fa : 06
                 * fb : 02
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20190218Bean {
            /**
             * temperature : 6℃~10℃
             * weather : 阴
             * weather_id : {"fa":"02","fb":"02"}
             * wind : 东南风微风
             * week : 星期一
             * date : 20190218
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXXX {
                /**
                 * fa : 02
                 * fb : 02
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20190219Bean {
            /**
             * temperature : 3℃~7℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 北风微风
             * week : 星期二
             * date : 20190219
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXXXX {
                /**
                 * fa : 07
                 * fb : 07
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20190220Bean {
            /**
             * temperature : 2℃~6℃
             * weather : 雨夹雪转阴
             * weather_id : {"fa":"06","fb":"02"}
             * wind : 东北风微风
             * week : 星期三
             * date : 20190220
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXXXXX {
                /**
                 * fa : 06
                 * fb : 02
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }
    }
}
