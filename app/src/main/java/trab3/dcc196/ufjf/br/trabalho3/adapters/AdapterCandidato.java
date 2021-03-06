package trab3.dcc196.ufjf.br.trabalho3.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoContract;
import trab3.dcc196.ufjf.br.trabalho3.R;

public class AdapterCandidato extends RecyclerView.Adapter<AdapterCandidato.ViewHolder> {
    private OnAdapterCandidatoClickListener listener;
    private Cursor cursor;

    public AdapterCandidato(Cursor c) {
        this.cursor = c;
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public void setOnAdapterCandidatoClickListener(OnAdapterCandidatoClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.candidato_layout_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int columnIndexNomeCompleto = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME);
        int columnIndexCPF = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF);
        cursor.moveToPosition(position);
        viewHolder.txtNomeCompleto.setText(cursor.getString(columnIndexNomeCompleto));
        viewHolder.txtCPF.setText(cursor.getString(columnIndexCPF));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @Override
    public long getItemId(int position) {
        int columnIndex = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD._ID);
        cursor.moveToPosition(position);

        return cursor.getInt(cursor.getColumnIndex(CandidatoContract.CandidatoBD._ID));
    }

    public interface OnAdapterCandidatoClickListener {
        void OnAdapterCandidatoClick(View view, int position);

        void OnAdapterCandidatoClickLong(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNomeCompleto;
        public TextView txtCPF;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeCompleto = itemView.findViewById(R.id.txt_nome);
            txtCPF = itemView.findViewById(R.id.txt_cpf);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnAdapterCandidatoClick(v, adapterPosition);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnAdapterCandidatoClickLong(v, adapterPosition);
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.OnAdapterCandidatoClick(v, adapterPosition);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.OnAdapterCandidatoClickLong(v, adapterPosition);
                }
            }
            return false;
        }
    }
}
