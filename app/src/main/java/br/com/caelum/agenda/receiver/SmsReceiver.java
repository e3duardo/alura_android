package br.com.caelum.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.agenda.R;
import br.com.caelum.agenda.dao.AlunoDao;

/**
 * Created by eduardo on 30/12/16.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
            byte[] pdu = (byte[]) pdus[0];
            String formato = (String) intent.getSerializableExtra("format");

            SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

            String telefone = sms.getDisplayOriginatingAddress();
            AlunoDao dao = new AlunoDao(context);
            if (dao.isAluno(telefone)) {
                Toast.makeText(context, "Chegou um SMS de Aluno!", Toast.LENGTH_SHORT).show();
                MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
                mp.start();
            }
            dao.close();
        }
    }
}
