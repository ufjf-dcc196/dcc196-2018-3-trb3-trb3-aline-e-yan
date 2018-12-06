package trab3.dcc196.ufjf.br.trabalho3.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import trab3.dcc196.ufjf.br.trabalho3.R;

public class AdapterEscola extends RecyclerView.Adapter<AdapterEscola.ViewHolder> {
    private OnAdapterEscolaClickListener listener;
    private Cursor cursor;

    public AdapterEscola(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c){
        cursor = c;
        notifyDataSetChanged();
    }

    public interface OnAdapterEscolaClickListener {
        void OnAdapterEscolaClick(View view, int position);
        void OnAdapterEstudanteClickLong(View view, int position);
    }

    public void setOnAdapterEstudanteClickListener(OnAdapterEscolaClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterEscola.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.escola_layout_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEscola.ViewHolder viewHolder, int i) {
        //int columnIndexNomeCompleto = cursor.getColumnIndexOrThrow(Trabalho3Contract.Escola.COLUMN_NAME_NOME_COMPLETO);
        //cursor.moveToPosition(i);
        //viewHolder.txtNomeCompleto.setText(cursor.getString(columnIndexNomeCompleto));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNomeCompleto;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeCompleto = itemView.findViewById(R.id.txt_nome);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int adapterPosition = getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            listener.OnAdapterEscolaClick(v, adapterPosition);
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
                            listener.OnAdapterEstudanteClickLong(v, adapterPosition);
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
                    listener.OnAdapterEscolaClick(v, adapterPosition);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                int adapterPosition = getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.OnAdapterEstudanteClickLong(v, adapterPosition);
                }
            }
            return false;
        }
    }
}
