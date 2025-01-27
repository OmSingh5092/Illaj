package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenite.ilaaj.api.controllers.UserController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.RecyclerBookedAppointmentBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookedAppointmentsAdapter extends RecyclerView.Adapter<BookedAppointmentsAdapter.ViewHolder> {
    RecyclerBookedAppointmentBinding binding;
    Context context;
    List<Appointment> data = new ArrayList<>();
    ItemClickHandler handler;

    public BookedAppointmentsAdapter(Context context, List<Appointment> data, ItemClickHandler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerBookedAppointmentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = data.get(position);
        new UserController(context).getSpecificUser(appointment.getDoctorId(), new UserController.userGetHandler() {
            @Override
            public void onSuccess(User user) {
                holder.doctor.setText(user.getName());
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctor, date, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor = binding.doctorName;
            date = binding.date;
            time = binding.time;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler.onViewClick(getAdapterPosition());
                }
            });
        }
    }
}
