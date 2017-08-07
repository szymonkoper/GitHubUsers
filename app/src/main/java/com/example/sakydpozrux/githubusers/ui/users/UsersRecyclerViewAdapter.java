package com.example.sakydpozrux.githubusers.ui.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sakydpozrux.githubusers.R;
import com.example.sakydpozrux.githubusers.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sakydpozrux on 07/08/2017.
 */

public class UsersRecyclerViewAdapter
        extends RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder> {
    private final List<User> users;
    private final OnItemClickListener listener;

    public UsersRecyclerViewAdapter(List<User> users, OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = users.get(position);
        holder.loginView.setText(users.get(position).login);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onItemClick(holder.user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;

        @BindView(R.id.tv_login) TextView loginView;

        public User user;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + loginView.getText() + "'";
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }
}
