package com.fiap.lanchonete.domain.utils;

import java.util.concurrent.TimeoutException;

public class RetryThread implements Runnable {
    private int maxRetries; // Número máximo de tentativas
    private boolean success; // Variável para verificar se a tarefa foi bem-sucedida

    public RetryThread(int maxRetries) {
        this.maxRetries = maxRetries;
        this.success = false;
    }

    @Override
    public void run() {
        int retries = 0;
        while (!success && retries < maxRetries) {
            try {
                // Realize a tarefa que você deseja executar na thread aqui
                // Por exemplo, uma operação de rede ou IO que pode lançar um TimeoutException

                // Simule um cenário de timeout
                throw new TimeoutException("Operação expirou");

                // Se a tarefa for concluída com sucesso, defina a variável success como true
                // success = true;
            } catch (TimeoutException e) {
                System.out.println("Timeout da tarefa, tentando novamente...");
                retries++;
            }
        }

        if (success) {
            System.out.println("Tarefa concluída com sucesso!");
        } else {
            System.out.println("Tentativas esgotadas, não foi possível concluir a tarefa.");
        }
    }
}
