package com.ManyThread;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test11 {

    //终止模式之两阶段终止模式
    //在一个线程T1中如何优雅终止线程T2？这里的优雅指的是给T2一个料理后事的机会。

    public static void main(String[] args) throws InterruptedException {
        TPT tpt = new TPT();
        tpt.start();
        Thread.sleep(3500);
        log.debug("stop");
        tpt.stop();
    }


    public static class TPT{
        private  Thread thread;
        public void start(){
            thread = new Thread(() ->{

                //开始监控
                while (true){
                    Thread current = Thread.currentThread();
                    if(current.isInterrupted()){
                        log.debug("料理后事");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        log.debug("将结果进行保存,进行监控记录");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        current.interrupt();
                    }
                    //执行监控操作
                }

            },"监控线程");
            thread.start();
        }
        public void stop(){
            thread.interrupt();
        }
    }

}
